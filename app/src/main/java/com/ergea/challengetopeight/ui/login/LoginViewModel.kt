package com.ergea.challengetopeight.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.ergea.challengetopeight.data.local.datastore.DataStoreManager
import com.ergea.challengetopeight.data.repository.UserRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val userRepository: UserRepository,
    private val provideDataStoreManager: DataStoreManager
) : ViewModel() {

    fun validateUser(email: String, password: String) =
        userRepository.validateUser(email, password)

    fun getUserByEmail(email: String) = userRepository.getUserByEmail(email)

    fun setIsLogin(status: Boolean) {
        viewModelScope.launch {
            provideDataStoreManager.setIsLogin(status)
        }
    }

    fun setId(id: Int) {
        viewModelScope.launch {
            provideDataStoreManager.setId(id)
        }
    }

    fun getDataStoreIsLogin(): LiveData<Boolean> {
        return provideDataStoreManager.getIsLogin.asLiveData()
    }
}