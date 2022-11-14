package com.ergea.challengetopeight.data.local.database

import androidx.room.*

@Dao
interface UserDao {
    @Query("SELECT * from user")
    suspend fun getAllUsers(): List<UserEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: UserEntity)

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email = :email)")
    fun getIfUserExistsByEmail(email: String): Boolean

    @Query("SELECT * FROM user WHERE id_user = :id")
    suspend fun getUserById(id: Int): UserEntity

    @Query("SELECT * FROM user WHERE email = :email")
    fun getUserByEmail(email: String): UserEntity

    @Query("UPDATE user SET username=:username, nama_lengkap=:fullname, tanggal_lahir=:tanggalLahir, alamat=:alamat WHERE id_user=:userId")
    suspend fun updateUser(
        userId: Int,
        username: String,
        fullname: String,
        tanggalLahir: String,
        alamat: String
    )

    @Query("SELECT EXISTS(SELECT * FROM user WHERE email = :email AND password = :password)")
    fun validateUser(email: String, password: String): Boolean
}