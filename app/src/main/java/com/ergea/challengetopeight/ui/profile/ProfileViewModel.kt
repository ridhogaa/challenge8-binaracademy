package com.ergea.challengetopeight.ui.profile

import androidx.lifecycle.*
import com.ergea.challengetopeight.data.local.database.UserEntity
import com.ergea.challengetopeight.data.local.datastore.DataStoreManager
import com.ergea.challengetopeight.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val provideDataStoreManager: DataStoreManager,
    private val userRepository: UserRepository
) :
    ViewModel() {

    private val _user: MutableLiveData<UserEntity> = MutableLiveData()
    val user: LiveData<UserEntity> get() = _user

    fun getUserById(id: Int) = CoroutineScope(Dispatchers.IO).launch {
        try {
            _user.postValue(userRepository.getUserById(id))
        } catch (e: Exception) {
            throw e
        }
    }

    fun updateUser(
        userId: Int,
        username: String,
        fullname: String,
        tanggalLahir: String,
        alamat: String
    ) = CoroutineScope(Dispatchers.IO).launch {
        userRepository.updateUser(userId, username, fullname, tanggalLahir, alamat)
    }

    fun removeIsLogin() =
        viewModelScope.launch {
            provideDataStoreManager.removeIsLogin()
        }


    fun removeId() =
        viewModelScope.launch {
            provideDataStoreManager.removeId()
        }

    fun getDataStoreId(): LiveData<Int> = provideDataStoreManager.getId.asLiveData()

}