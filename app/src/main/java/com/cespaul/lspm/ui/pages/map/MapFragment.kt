package com.cespaul.lspm.ui.pages.map

import android.annotation.SuppressLint
import android.location.Location
import android.os.Bundle
import android.os.Looper.getMainLooper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment
import com.cespaul.lspm.ui.MainView
import com.google.android.material.snackbar.Snackbar
import com.mapbox.android.core.location.*
import com.mapbox.android.core.permissions.PermissionsListener
import com.mapbox.android.core.permissions.PermissionsManager
import com.mapbox.mapboxsdk.location.LocationComponentActivationOptions
import com.mapbox.mapboxsdk.location.LocationComponentOptions
import com.mapbox.mapboxsdk.location.OnLocationCameraTransitionListener
import com.mapbox.mapboxsdk.location.modes.CameraMode
import com.mapbox.mapboxsdk.location.modes.RenderMode
import com.mapbox.mapboxsdk.maps.MapView
import com.mapbox.mapboxsdk.maps.MapboxMap
import com.mapbox.mapboxsdk.maps.OnMapReadyCallback
import com.mapbox.mapboxsdk.maps.Style
import kotlinx.android.synthetic.main.fragment_map.*
import java.lang.ref.WeakReference


class MapFragment(private val mainView: MainView) : BaseFragment<MapPresenter>(), MapUIView,
    OnMapReadyCallback,
    PermissionsListener {

    private lateinit var snackbar: Snackbar

    internal val activityView = mainView

    private lateinit var mapView: MapView

    private var callback = MapFragmentLocationCallback(this)
    private val DEFAULT_INTERVAL_IN_MILLISECONDS = 1000L
    private val DEFAULT_MAX_WAIT_TIME = DEFAULT_INTERVAL_IN_MILLISECONDS * 5
    private var permissionsManager: PermissionsManager = PermissionsManager(this)
    private lateinit var locationEngine: LocationEngine
    internal lateinit var mapboxMap: MapboxMap
    internal lateinit var locationTextView: TextView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_map, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mapView = map_view
        locationTextView = location_coordinates
        snackbar = mainView.initSnackbar("", false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        mapView.onCreate(savedInstanceState)
        mapView.getMapAsync(this)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        permissionsManager.onRequestPermissionsResult(requestCode, permissions, grantResults)
        snackbar.dismiss()
    }

    override fun onExplanationNeeded(permissionsToExplain: List<String>) {
        // Не требуется объяснение разрешения.
    }

    override fun onPermissionResult(granted: Boolean) {
        if (granted) {
            mapView.getMapAsync {
                onMapReady(it)
            }
        } else {
            snackbar = mainView.initSnackbar(
                getString(R.string.user_location_permission_not_granted),
                true
            )
            snackbar.setAction(
                getString(R.string.user_location_permission_request)
            ) {
                permissionsManager = PermissionsManager(this)
                permissionsManager.requestLocationPermissions(requireActivity())
            }
            snackbar.show()
            return
        }
    }

    @SuppressLint("MissingPermission")
    private fun initLocationEngine() {
        locationEngine = LocationEngineProvider.getBestLocationEngine(requireContext())
        val request = LocationEngineRequest.Builder(DEFAULT_INTERVAL_IN_MILLISECONDS)
            .setPriority(LocationEngineRequest.PRIORITY_HIGH_ACCURACY)
            .setMaxWaitTime(DEFAULT_MAX_WAIT_TIME).build()
        locationEngine.requestLocationUpdates(request, callback, getMainLooper())
        locationEngine.getLastLocation(callback)
    }

    override fun onMapReady(mapboxMap: MapboxMap) {
        this.mapboxMap = mapboxMap
        mapboxMap.setStyle(
            Style.OUTDOORS
        ) { style -> enableLocationComponent(style) }
    }

    @SuppressLint("MissingPermission")
    private fun enableLocationComponent(loadedMapStyle: Style) {
        // Check if permissions are enabled and if not request
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {

            // Create and customize the LocationComponent's options
            val customLocationComponentOptions = LocationComponentOptions.builder(requireContext())
                .trackingGesturesManagement(true)
                .accuracyColor(ContextCompat.getColor(requireContext(), R.color.mapboxGreen))
                .build()

            val locationComponentActivationOptions = LocationComponentActivationOptions.builder(
                requireContext(),
                loadedMapStyle
            )
                .locationComponentOptions(customLocationComponentOptions)
                .useDefaultLocationEngine(false)
                .build()

            // Get an instance of the LocationComponent and then adjust its settings
            mapboxMap.locationComponent.apply {

                // Activate the LocationComponent with options
                activateLocationComponent(locationComponentActivationOptions)

                // Enable to make the LocationComponent visible
                isLocationComponentEnabled = true

                // Set the LocationComponent's render mode
                renderMode = RenderMode.COMPASS
            }

            mapboxMap.locationComponent.setCameraMode(CameraMode.TRACKING_GPS_NORTH, object :
                OnLocationCameraTransitionListener {
                override fun onLocationCameraTransitionFinished(cameraMode: Int) {
                    mapboxMap.locationComponent.zoomWhileTracking(15.0, 2000L)
                }

                override fun onLocationCameraTransitionCanceled(cameraMode: Int) {}
            })
            initLocationEngine()
        } else {
            permissionsManager = PermissionsManager(this)
            permissionsManager.requestLocationPermissions(requireActivity())
        }
    }

    override fun onStart() {
        super.onStart()
        mapView.onStart()
    }

    override fun onResume() {
        super.onResume()
        mapView.onResume()
        if (PermissionsManager.areLocationPermissionsGranted(requireContext())) {
            mapView.getMapAsync {
                onMapReady(it)
            }
        } else {
            snackbar = mainView.initSnackbar(
                getString(R.string.user_location_permission_not_granted),
                true
            )
            snackbar.setAction(
                getString(R.string.user_location_permission_request)
            ) {
                permissionsManager = PermissionsManager(this)
                permissionsManager.requestLocationPermissions(requireActivity())
            }
            snackbar.show()
        }
    }

    override fun onPause() {
        super.onPause()
        mapView.onPause()
        snackbar.dismiss()
    }

    override fun onStop() {
        super.onStop()
        mapView.onStop()
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        mapView.onSaveInstanceState(outState)
    }

    override fun onDestroy() {
        super.onDestroy()
        mapView.onDestroy()
    }

    override fun onLowMemory() {
        super.onLowMemory()
        mapView.onLowMemory()
    }

    override fun instantiatePresenter(): MapPresenter {
        return MapPresenter(this)
    }
}

class MapFragmentLocationCallback internal constructor(fragment: MapFragment?) :
    LocationEngineCallback<LocationEngineResult?> {
    private val activityWeakReference: WeakReference<MapFragment> = WeakReference(fragment)

    /**
     * The LocationEngineCallback interface's method which fires when the device's location has changed.
     *
     * @param result the LocationEngineResult object which has the last known location within it.
     */
    override fun onSuccess(result: LocationEngineResult?) {
        val fragment: MapFragment? = activityWeakReference.get()
        val location: Location = result?.lastLocation ?: return
        val coordinates = "lat: ${location.latitude} long: ${location.longitude}"

        fragment?.mapboxMap?.locationComponent?.setCameraMode(
            CameraMode.TRACKING_GPS_NORTH,
            object :
                OnLocationCameraTransitionListener {
                override fun onLocationCameraTransitionFinished(cameraMode: Int) {
                    fragment.mapboxMap.locationComponent.zoomWhileTracking(15.0, 2000L)
                }

                override fun onLocationCameraTransitionCanceled(cameraMode: Int) {}
            })

        fragment?.locationTextView?.text = coordinates

        // Pass the new location to the Maps SDK's LocationComponent
        fragment?.mapboxMap?.locationComponent?.forceLocationUpdate(result.lastLocation) ?: return
    }

    /**
     * The LocationEngineCallback interface's method which fires when the device's location can't be captured
     *
     * @param exception the exception message
     */
    override fun onFailure(exception: Exception) {
        Log.d("onLocationFailed", exception.localizedMessage, exception)
        val fragment: MapFragment? = activityWeakReference.get()
        fragment?.mapboxMap?.locationComponent?.apply {
            cameraMode = CameraMode.NONE
        }
        val snackbar = fragment?.activityView?.initSnackbar(
            fragment.getString(R.string.user_location_do_not_locate),
            false
        )
        snackbar?.show()
    }
}