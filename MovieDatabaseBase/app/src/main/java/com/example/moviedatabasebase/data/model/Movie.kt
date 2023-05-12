package com.example.moviedatabasebase.data.model

import androidx.annotation.IdRes
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey

//@Entity(tableName = "MovieList")
data class Movie(
  //  @PrimaryKey
    val page : Int,
   // @Ignore
    val results: List<Result>?,
){
    constructor(page : Int) : this(page,null) {
       // this(page, null)
    }
}

@Entity(tableName = "Result")
data class Result(
    @PrimaryKey(autoGenerate = true)
    val id : Int,
    val title : String,
    val overview : String,
    val poster_path : String
)
