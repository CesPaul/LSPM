package com.cespaul.lspm.ui.pages.parsing

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.cespaul.lspm.R
import com.cespaul.lspm.base.fragment.BaseFragment
import com.cespaul.lspm.model.Quotes
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_parsing.*

class ParsingFragment : BaseFragment<ParsingPresenter>(), ParsingView {

    private lateinit var snackbar: Snackbar

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_parsing, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val layoutManager = LinearLayoutManager(context)
        val parsingRecycler = parsing_recycler
        parsingRecycler.layoutManager = layoutManager
        parsingRecycler.adapter = presenter.adapter
        val dividerItemDecoration = DividerItemDecoration(
            parsingRecycler.context,
            layoutManager.orientation
        )
        parsingRecycler.addItemDecoration(dividerItemDecoration)

        snackbar = Snackbar.make(
            requireActivity().mainBottomNavigation,
            getString(R.string.error_load_quotes_message),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(
                getString(R.string.request_to_repeat)
            ) {
                presenter.onViewResumed()
            }
            .setAnchorView(requireActivity().mainBottomNavigation)

        presenter.onViewCreated()
    }

    override fun onResume() {
        super.onResume()
        presenter.onViewResumed()
    }

    override fun onPause() {
        snackbar.dismiss()
        super.onPause()
    }

    override fun onStop() {
        hideSnackbar()
        super.onStop()
    }

    override fun instantiatePresenter(): ParsingPresenter {
        return ParsingPresenter(this)
    }

    override fun updateQuotes(quotes: Quotes) {
        presenter.adapter.updateList(quotes)
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter.onViewDestroyed()
    }

    override fun showSnackbar() {
        snackbar.show()
    }

    override fun hideSnackbar() {
        snackbar.dismiss()
    }

    override fun initSnackbar() {
        snackbar = Snackbar.make(
            requireActivity().mainBottomNavigation,
            getString(R.string.error_load_quotes_message),
            Snackbar.LENGTH_INDEFINITE
        )
            .setAction(
                getString(R.string.request_to_repeat)
            ) {
                presenter.onViewResumed()
            }
            .setAnchorView(requireActivity().mainBottomNavigation)
        snackbar.show()
    }

    override fun showProgress() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgress() {
        progressBar.visibility = View.INVISIBLE
    }
}