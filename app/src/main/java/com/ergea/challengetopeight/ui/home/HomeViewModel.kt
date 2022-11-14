package com.ergea.challengetopeight.ui.home

import androidx.lifecycle.*
import com.ergea.challengetopeight.data.local.database.UserEntity
import com.ergea.challengetopeight.data.local.datastore.DataStoreManager
import com.ergea.challengetopeight.data.remote.model.GetMoviePopularResponse
import com.ergea.challengetopeight.data.remote.model.ResultMovies
import com.ergea.challengetopeight.data.repository.MovieRepository
import com.ergea.challengetopeight.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val userRepository: UserRepository,
    private val provideDataStoreManager: DataStoreManager
) :
    ViewModel() {

    init {
        getPopular()
    }

    private val _movie = MutableLiveData<List<ResultMovies>>()
    val movie: LiveData<List<ResultMovies>> get() = _movie

    private val _user = MutableLiveData<UserEntity>()
    val user: LiveData<UserEntity> get() = _user

    private fun getPopular() = viewModelScope.launch {
        movieRepository.getMovieList().enqueue(object : Callback<GetMoviePopularResponse> {
            override fun onResponse(
                call: Call<GetMoviePopularResponse>,
                response: Response<GetMoviePopularResponse>
            ) {
                if (response.isSuccessful) {
                    val data = response.body()
                    if (data != null) {
                        _movie.postValue(data.results)
                    }
                }
            }

            override fun onFailure(call: Call<GetMoviePopularResponse>, t: Throwable) {

            }

        })
    }


    fun getUserById(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        try {
            _user.postValue(userRepository.getUserById(id))
        } catch (e: Exception) {
            throw e
        }
    }

    fun getDataStoreId(): LiveData<Int> = provideDataStoreManager.getId.asLiveData()
}