package com.ergea.challengetopeight.data.remote

import com.ergea.challengetopeight.data.remote.model.GetMovieDetailResponse
import com.ergea.challengetopeight.data.remote.model.GetMoviePopularResponse
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.*

interface ApiServiceMovie {
    @GET("3/movie/popular?api_key=9428967aca5607f7a2bbcb7a46f0ecfe")
    fun getMovieList(): Call<GetMoviePopularResponse>

    @GET("3/movie/{id}?api_key=9428967aca5607f7a2bbcb7a46f0ecfe")
    suspend fun getMovieDetail(@Path("id") id: Int): Response<GetMovieDetailResponse>
}