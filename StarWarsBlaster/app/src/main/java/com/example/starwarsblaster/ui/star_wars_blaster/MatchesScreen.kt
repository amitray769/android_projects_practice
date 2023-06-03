package com.example.starwarsblaster.ui.star_wars_blaster

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.starwarsblaster.adpater.MatchesScreenAdapter
import com.example.starwarsblaster.databinding.ActivityMatchesScreenBinding
import com.example.starwarsblaster.model.PlayerData
import com.example.starwarsblaster.model.PlayerScores
import com.example.starwarsblaster.model.PlayerScoresItem
import com.example.starwarsblaster.ui.base.TournamentViewModelFactory
import kotlin.properties.Delegates

class MatchesScreen : AppCompatActivity() {
    private lateinit var viewModel : TournamentViewModel
    private lateinit var binding : ActivityMatchesScreenBinding
    private lateinit var adapter : MatchesScreenAdapter
    private var id by Delegates.notNull<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMatchesScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(this, TournamentViewModelFactory(application))[TournamentViewModel::class.java]
        id = intent.getIntExtra("id",0)


     //   setupRecyclerView(viewModel.playerScores,viewModel.playerData)
        setupRecyclerView(viewModel.getTheMatchesOfThePlayer(id), viewModel.playerData)
    }



    private fun setupRecyclerView(playerMatches : ArrayList<PlayerScoresItem>, playerData: PlayerData) {
        adapter = MatchesScreenAdapter(this)
        binding.rvMatches.adapter = adapter
        binding.rvMatches.layoutManager = LinearLayoutManager(this)

        adapter.updateList(playerMatches, playerData)

    }
}