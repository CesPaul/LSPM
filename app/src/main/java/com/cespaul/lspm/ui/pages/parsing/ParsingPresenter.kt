package com.cespaul.lspm.ui.pages.parsing

import android.util.Log
import com.cespaul.lspm.base.fragment.BaseFragmentPresenter
import com.cespaul.lspm.data.network.ParsingApi
import com.cespaul.lspm.data.repository.ParsingRepository
import com.cespaul.lspm.data.repository.ParsingRepositoryImpl
import io.reactivex.disposables.Disposable
import javax.inject.Inject

class ParsingPresenter(parsingView: ParsingView) :
    BaseFragmentPresenter<ParsingView>(parsingView) {

    @Inject
    lateinit var parsingApi: ParsingApi

    private var started: Disposable? = null

    private var resumed: Disposable? = null

    private val repository: ParsingRepository = ParsingRepositoryImpl(parsingApi)

    var adapter: ParsingRvAdapter = ParsingRvAdapter(
        viewFragment.getFragmentContext(),
        repository
    )

    private fun loadQuotes(): Disposable? {
        viewFragment.showProgress()
        return repository
            .loadQuotes()
            .doOnTerminate {
                Log.d("get_quotes", "in process")
            }
            .subscribe({
                viewFragment.hideProgress()
                adapter.updateList(it)
                Log.d("get_quotes", "success")
            },
                {
                    viewFragment.hideProgress()
                    Log.d("get_quotes", "error")
                }
            )
    }

    private fun reloadQuotes(): Disposable? {
        viewFragment.showProgress()
        return repository
            .loadQuotes()
            .doOnTerminate {
                Log.d("get_quotes", "in process")
            }
            .subscribe({
                viewFragment.hideProgress()
                adapter.updateList(it)
                Log.d("get_quotes", "success")
            },
                {
                    viewFragment.initSnackbar()
                    viewFragment.hideProgress()
                    Log.d("get_quotes", "error")
                }
            )
    }

    override fun onViewCreated() {
        started = loadQuotes()
    }

    fun onViewResumed() {
        resumed = reloadQuotes()
    }

    override fun onViewDestroyed() {
        started?.dispose()
        resumed?.dispose()
    }
}