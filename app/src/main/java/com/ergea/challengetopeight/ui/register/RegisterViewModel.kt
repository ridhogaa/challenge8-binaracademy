package com.ergea.challengetopeight.ui.register

import androidx.lifecycle.ViewModel
import com.ergea.challengetopeight.data.repository.UserRepository
import com.ergea.challengetopeight.data.local.database.UserEntity
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor(private val userRepository: UserRepository) :
    ViewModel() {

    fun insertUser(userEntity: UserEntity) = CoroutineScope(Dispatchers.IO).launch {
        try {
            userRepository.insertUser(userEntity)
        } catch (ex: Exception) {
            throw ex
        }
    }

    fun getIfUserExistsByEmail(email: String) = userRepository.getIfUserExistsByEmail(email)


}