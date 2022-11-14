@file:Suppress("unused")

package com.ergea.challengetopeight.data.repository

import com.ergea.challengetopeight.data.local.database.UserDao
import com.ergea.challengetopeight.data.local.database.UserEntity
import javax.inject.Inject

class UserRepository @Inject constructor(
    private val userDao: UserDao
) {
    suspend fun getAllUsers(): List<UserEntity> = userDao.getAllUsers()

    suspend fun insertUser(userEntity: UserEntity) =
        userDao.insertUser(userEntity)

    fun getIfUserExistsByEmail(email: String) =
        userDao.getIfUserExistsByEmail(email)

    suspend fun updateUser(
        userId: Int,
        username: String,
        fullname: String,
        tanggalLahir: String,
        alamat: String
    ) = userDao.updateUser(userId, username, fullname, tanggalLahir, alamat)

    suspend fun getUserById(id: Int): UserEntity = userDao.getUserById(id)

    fun getUserByEmail(email: String): UserEntity = userDao.getUserByEmail(email)

    fun validateUser(email: String, password: String) =
        userDao.validateUser(email, password)
}