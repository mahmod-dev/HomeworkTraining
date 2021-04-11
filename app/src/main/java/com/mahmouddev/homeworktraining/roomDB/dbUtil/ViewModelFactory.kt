package com.mahmouddev.homeworktraining.roomDB.dbUtil

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.homeworktraining.roomDB.DatabaseHelper
import com.mahmouddev.homeworktraining.viewmodel.UserViewModel


class ViewModelFactory(private val dbHelper: DatabaseHelper) :
    ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(dbHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }


}
