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
import com.cespaul.lspm.ui.MainView
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fragment_parsing.*

class ParsingFragment(private val mainView: MainView) : BaseFragment<ParsingPresenter>(),
    ParsingView {

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

        snackbar = mainView.initSnackbar("", false)

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
        snackbar.dismiss()
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

    override fun initAndShowSnackbar() {
        snackbar = mainView.initSnackbar(getString(R.string.error_load_quotes_message), true)
        snackbar.setAction(
            getString(R.string.request_to_repeat)
        ) {
            presenter.onViewResumed()
        }
        snackbar.show()
    }

    override fun visibilityProgressBar(isVisible: Boolean) {
        when (isVisible) {
            true -> progressBar.visibility = View.VISIBLE
            false -> progressBar.visibility = View.INVISIBLE
        }
    }
}