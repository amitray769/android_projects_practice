package com.example.starwarsblaster.model


import com.example.starwarsblaster.ui.star_wars_blaster.MatchesScreen
import com.google.gson.annotations.SerializedName

data class PlayerDataItem(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("point")
    var point: Int   ,
    @SerializedName("matches")
    var matches : ArrayList<PlayerScoresItem> = ArrayList()
)