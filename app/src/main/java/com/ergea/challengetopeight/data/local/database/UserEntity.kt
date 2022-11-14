package com.ergea.challengetopeight.data.local.database

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user")
class UserEntity(
    @PrimaryKey(autoGenerate = true)
    @field:ColumnInfo(name = "id_user")
    var id_user: Int? = null,
    @field:ColumnInfo(name = "username")
    val username: String? = null,
    @field:ColumnInfo(name = "email")
    val email: String? = null,
    @field:ColumnInfo(name = "password")
    val password: String? = null,
    @field:ColumnInfo(name = "nama_lengkap")
    val namaLengkap: String? = null,
    @field:ColumnInfo(name = "tanggal_lahir")
    val tanggalLahir: String? = null,
    @field:ColumnInfo(name = "alamat")
    val alamat: String? = null
) : Parcelable
