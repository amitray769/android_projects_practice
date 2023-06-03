package com.example.starwarsblaster.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.starwarsblaster.R
import com.example.starwarsblaster.databinding.ItemMatchesBinding
import com.example.starwarsblaster.model.PlayerData
import com.example.starwarsblaster.model.PlayerScores
import com.example.starwarsblaster.model.PlayerScoresItem
import com.example.starwarsblaster.model.WinningState

class MatchesScreenAdapter(private val context: Context) : RecyclerView.Adapter<MatchesScreenAdapter.MatchesViewHolder>() {
    private var playerScores : ArrayList<PlayerScoresItem>? = null
    private var playerData : PlayerData? = null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MatchesViewHolder {
        val view = ItemMatchesBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MatchesViewHolder(view)
    }

    override fun getItemCount(): Int {
        return playerScores?.size ?: 0
    }

    override fun onBindViewHolder(holder: MatchesViewHolder, position: Int) {
        playerScores?.get(position)?.let { holder.setData(it) }
    }

     fun updateList(playerScores : ArrayList<PlayerScoresItem>,playerData: PlayerData) {
        this.playerScores = playerScores
         this.playerData = playerData
        notifyDataSetChanged()
    }

    inner class MatchesViewHolder(private val itemMatchesBinding: ItemMatchesBinding) :
        RecyclerView.ViewHolder(itemMatchesBinding.root) {
            fun setData(playerScoresItem: PlayerScoresItem){
                setBackgroundColor(WinningState.WON)
               itemMatchesBinding.tvPlayer1.text = getNameForTheId(playerScoresItem.player1.id)
               itemMatchesBinding.tvScore.text =  "${playerScoresItem.player1.score} - ${playerScoresItem.player2.score}"
               itemMatchesBinding.tvPlayer2.text = getNameForTheId(playerScoresItem.player2.id)
            }

      private  fun getNameForTheId(id : Int) : String{
            var name = ""
            for (player in playerData!!){
                if (player.id == id){
                    name = player.name
                    break
                }
            }
            return name
        }

        private fun setBackgroundColor(winningState: WinningState){
            val color: Int = when(winningState){
                WinningState.WON -> R.color.green
                WinningState.LOST -> R.color.red
                WinningState.DRAW -> R.color.white
            }
            itemMatchesBinding.root.background =context.getDrawable(color)

        }
    }




}