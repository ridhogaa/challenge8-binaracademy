package com.ergea.challengetopeight.data.repository

import com.ergea.challengetopeight.data.remote.ApiServiceMovie
import javax.inject.Inject

class MovieRepository @Inject constructor(
    private val apiServiceMovie: ApiServiceMovie
) {
    fun getMovieList() = apiServiceMovie.getMovieList()
    suspend fun getMovieDetail(id: Int) = apiServiceMovie.getMovieDetail(id)
}