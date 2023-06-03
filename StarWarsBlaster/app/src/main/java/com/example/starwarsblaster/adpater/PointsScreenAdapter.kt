package com.example.starwarsblaster.adpater

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.starwarsblaster.databinding.ItemPointsBinding
import com.example.starwarsblaster.model.PlayerData
import com.example.starwarsblaster.model.PlayerDataItem

class PointsScreenAdapter(private val context: Context, private val listner : OnClickPlayerItem) : RecyclerView.Adapter<PointsScreenAdapter.PointsScreenViewholder>() {
    private var playerData : PlayerData? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PointsScreenViewholder {
        val view = ItemPointsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PointsScreenViewholder(view)
    }

    override fun getItemCount(): Int {
        return playerData?.size ?: 0
    }

    override fun onBindViewHolder(holder: PointsScreenViewholder, position: Int) {
        playerData?.get(position)?.let { holder.setData(it) }
    }

     fun updateList(playerData : PlayerData) {
        this.playerData = playerData
        notifyDataSetChanged()
    }

    inner class PointsScreenViewholder(private val itemPointsBinding: ItemPointsBinding) :
        RecyclerView.ViewHolder(itemPointsBinding.root) {
            fun setData(playerDataItem: PlayerDataItem){
               itemPointsBinding.tvPoint.text = playerDataItem.point.toString()
               itemPointsBinding.tvName.text = playerDataItem.name
                Glide.with(context).load(playerDataItem.icon).into(itemPointsBinding.ivPlayer)

                itemPointsBinding.root.setOnClickListener{
                    listner.onClick(playerDataItem.id)
                }
            }


    }

    interface OnClickPlayerItem{
        fun onClick(id : Int)
    }

}