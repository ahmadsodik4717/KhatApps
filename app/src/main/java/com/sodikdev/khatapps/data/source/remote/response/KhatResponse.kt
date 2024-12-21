package com.sodikdev.khatapps.data.source.remote.response

import com.google.gson.annotations.SerializedName

data class KhatResponse(

    @field:SerializedName("predictions")
    val predictions: List<PredictionsItem>
)

data class PredictionsItem(

    @field:SerializedName("confidence")
    val confidence: Double,

    @field:SerializedName("class")
    val classDetect: String
)