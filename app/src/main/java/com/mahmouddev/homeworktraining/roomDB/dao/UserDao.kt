package com.mahmouddev.homeworktraining.roomDB.dao

import androidx.room.*
import com.mahmouddev.homeworktraining.roomDB.entities.User

@Dao
interface UserDao {

    @Query("SELECT * FROM user_tb WHERE username = :name AND password = :password  LIMIT 1")
    suspend fun getStudent(name: String, password: String): User?

    @Insert
    suspend fun insertStudent(user: User): Long

    @Update
    suspend fun updateStudent(user: User): Int

    @Delete
    suspend fun delete(user: User): Int

}