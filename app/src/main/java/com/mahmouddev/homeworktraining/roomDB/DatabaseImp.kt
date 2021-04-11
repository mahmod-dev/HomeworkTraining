package com.mahmouddev.homeworktraining.roomDB

import com.mahmouddev.homeworktraining.roomDB.dao.UserDao
import com.mahmouddev.homeworktraining.roomDB.entities.User

class DatabaseImp(var userDao: UserDao): DatabaseHelper {

    override suspend fun getUser(name: String, password: String): User? {
       return userDao.getStudent(name, password)
    }

    override suspend fun insertUser(user: User): Long {
        return userDao.insertStudent(user)
    }

}