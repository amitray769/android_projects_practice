package com.example.blankproject.data

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.blankproject.data.models.Result
import com.example.blankproject.data.remote.ApiService


class PlanetPagingSource(private val apiService: ApiService) : PagingSource<Int, Result>() {
    override fun getRefreshKey(state: PagingState<Int, Result>): Int? {
        TODO("Not yet implemented")
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Result> {

        val page = params.key ?: null
        return try {
            val response = apiService.getPlanets(1)
            LoadResult.Page(
                data = response.results,
                prevKey = if(page == 1) null else page?.minus(1),
                nextKey = if (response.next == null) null else page?.plus(1)
            )
        } catch (e: Exception) {

            LoadResult.Error(e)
        }

    }


}