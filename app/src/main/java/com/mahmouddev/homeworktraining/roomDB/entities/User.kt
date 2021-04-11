package com.mahmouddev.homeworktraining.roomDB.entities

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "user_tb")
data class User(
    var username: String,
    var age: String,
    var password: String,


    @PrimaryKey(autoGenerate = true)
    var id: Int = 0

) : Parcelable