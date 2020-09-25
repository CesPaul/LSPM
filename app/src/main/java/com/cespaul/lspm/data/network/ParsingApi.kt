package com.cespaul.lspm.data.network

import com.cespaul.lspm.model.Quotes
import io.reactivex.Observable
import retrofit2.http.GET

interface ParsingApi {
    @GET("quotes?sort=time")
    fun getQuotes(): Observable<Quotes>
}
