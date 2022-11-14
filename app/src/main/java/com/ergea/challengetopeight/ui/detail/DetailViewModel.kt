package com.ergea.challengetopeight.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ergea.challengetopeight.data.remote.model.GetMovieDetailResponse
import com.ergea.challengetopeight.data.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : ViewModel() {
    private val _movie: MutableLiveData<Response<GetMovieDetailResponse>> = MutableLiveData()
    val movie: LiveData<Response<GetMovieDetailResponse>> get() = _movie

    fun getMovieDetail(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        try {
            val response = movieRepository.getMovieDetail(id)
            _movie.postValue(response)
        } catch (e: Exception) {
            throw e
        }
    }
}