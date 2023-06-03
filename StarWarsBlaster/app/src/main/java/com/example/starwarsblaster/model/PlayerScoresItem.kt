package com.example.starwarsblaster.model


import com.google.gson.annotations.SerializedName

data class PlayerScoresItem(
    @SerializedName("match")
    val match: Int,
    @SerializedName("player1")
    val player1: Player,
    @SerializedName("player2")
    val player2: Player,
    var winningState : WinningState = WinningState.LOST
)

enum class WinningState{
  WON,LOST,DRAW
}