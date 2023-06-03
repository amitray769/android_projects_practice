package com.example.starwarsblaster.ui.star_wars_blaster

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsblaster.adpater.PointsScreenAdapter
import com.example.starwarsblaster.databinding.ActivityMainBinding
import com.example.starwarsblaster.model.PlayerData
import com.example.starwarsblaster.ui.base.TournamentViewModelFactory

class PointsScreen : AppCompatActivity(), PointsScreenAdapter.OnClickPlayerItem{
    lateinit var viewModel : TournamentViewModel
    lateinit var binding : ActivityMainBinding
    lateinit var adapter : PointsScreenAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, TournamentViewModelFactory(application))[TournamentViewModel::class.java]
        setupRecyclerView(viewModel.playerData)
    }

    private fun setupRecyclerView(playerData: PlayerData) {
        adapter = PointsScreenAdapter(this, this)
        binding.rvPoints.adapter = adapter
        binding.rvPoints.layoutManager = LinearLayoutManager(this)

        adapter.updateList(playerData)

    }

    private fun startActivityMatchesScreen(id: Int) {
        val intent = Intent(this, MatchesScreen::class.java)
        intent.putExtra("id",id)
        startActivity(intent)
    }

    override fun onClick(id: Int) {
        Toast.makeText(this, "Hellllooooooo id : $id", Toast.LENGTH_SHORT).show()
        startActivityMatchesScreen(id)

    }
}