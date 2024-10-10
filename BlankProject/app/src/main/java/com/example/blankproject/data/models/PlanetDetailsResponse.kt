package com.example.blankproject.data.models


import com.google.gson.annotations.SerializedName

data class PlanetDetailsResponse(
    @SerializedName("count")
    val count: Int,
    @SerializedName("next")
    val next: String,
    @SerializedName("previous")
    val previous: String,
    @SerializedName("results")
    val results: List<Result>
)