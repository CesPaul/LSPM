package com.cespaul.lspm.model

import com.google.gson.annotations.SerializedName

data class Quote(

    @SerializedName("id")
    val id: Int?,
    @SerializedName("description")
    val description: String?,
    @SerializedName("time")
    val time: String?,
    @SerializedName("rating")
    val rating: Int?

)