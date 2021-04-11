package com.mahmouddev.homeworktraining.roomDB

import com.mahmouddev.homeworktraining.roomDB.entities.User

interface DatabaseHelper {

    suspend fun getUser(name: String, password: String): User?

    suspend fun insertUser(user: User): Long

}