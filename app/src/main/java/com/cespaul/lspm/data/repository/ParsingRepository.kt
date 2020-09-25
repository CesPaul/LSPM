package com.cespaul.lspm.data.repository

import com.cespaul.lspm.model.Quote
import com.cespaul.lspm.model.Quotes
import io.reactivex.Observable

interface ParsingRepository {
    fun loadQuotes(): Observable<Quotes>

    fun getQuotesCount(): Int

    fun getQuoteAt(index: Int): Quote

    fun getQuotesList(): List<Quote>

    fun getQuotes(): Quotes
}