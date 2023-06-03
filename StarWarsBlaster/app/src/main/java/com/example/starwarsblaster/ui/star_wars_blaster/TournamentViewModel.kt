package com.example.starwarsblaster.ui.star_wars_blaster

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import com.example.starwarsblaster.model.PlayerData
import com.example.starwarsblaster.model.PlayerScores
import com.example.starwarsblaster.model.PlayerScoresItem
import com.example.starwarsblaster.model.WinningState
import com.google.gson.Gson

class TournamentViewModel(private val context: Application) : AndroidViewModel(context) {

    var playerData : PlayerData
    var playerScores: PlayerScores
    init {
        playerData = getPointsData()
        playerScores = getMatchesData()
        calculatePoints()
    }



    private fun calculatePoints(){
        for (playerScore in playerScores){
           if(playerScore.player1.score > playerScore.player2.score){
               playerScore.winningState = WinningState.WON
               findIdAddPoint(playerScore.player1.id, 3, playerScore)

           } else if (playerScore.player1.score < playerScore.player2.score){
               playerScore.winningState = WinningState.WON
               findIdAddPoint(playerScore.player2.id, 3, playerScore)

           } else {
               playerScore.winningState = WinningState.DRAW
               findIdAddPoint(playerScore.player1.id, 1, playerScore)
               findIdAddPoint(playerScore.player2.id, 1, playerScore)
           }
        }
    }

    private fun findIdAddPoint(id : Int, point : Int, playerScore : PlayerScoresItem){
        for ((index, player) in playerData.withIndex()){
            if (player.id == id){
                playerData[index].point += point
                playerData[index].matches.add(playerScore)
                playerData[index].matches

            }
        }
    }

    fun getTheMatchesOfThePlayer(id : Int) : ArrayList<PlayerScoresItem>{
        var matches = ArrayList<PlayerScoresItem>()
        for (player in playerData){
            if (player.id == id){
                matches = player.matches
            }
        }
        return matches
    }


    private fun getMatchesData(): PlayerScores {
        val inputStream = context.assets.open("StarWarsMatches.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()

        return gson.fromJson(json, PlayerScores::class.java)
    }

    private fun getPointsData() : PlayerData{
        val inputStream = context.assets.open("StarWarsPlayers.json")
        val size: Int = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()

        val json = String(buffer, Charsets.UTF_8)
        val gson = Gson()
        return gson.fromJson(json, PlayerData::class.java)
    }

}