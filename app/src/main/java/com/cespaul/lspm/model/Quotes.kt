package com.cespaul.lspm.model

import com.google.gson.annotations.SerializedName


data class Quotes(

    @SerializedName("quotes")
    val quotes: List<Quote>

)