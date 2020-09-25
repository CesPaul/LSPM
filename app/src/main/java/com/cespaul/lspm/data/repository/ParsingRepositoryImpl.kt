package com.cespaul.lspm.data.repository

import com.cespaul.lspm.data.network.ParsingApi
import com.cespaul.lspm.model.Quote
import com.cespaul.lspm.model.Quotes
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class ParsingRepositoryImpl(private val parsingApi: ParsingApi) : ParsingRepository {

    //private var tempQuotes = listOf(Quote(1, "test", "13:37", 5))

    private var quotesList: List<Quote> = listOf()

    override fun loadQuotes(): Observable<Quotes> {
        return parsingApi
            .getQuotes()
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
    }

    override fun getQuotesCount(): Int = quotesList.size

    override fun getQuoteAt(index: Int): Quote = quotesList[index]

    override fun getQuotes(): Quotes {
        return Quotes(quotesList)
    }

    override fun getQuotesList(): List<Quote> = quotesList
}