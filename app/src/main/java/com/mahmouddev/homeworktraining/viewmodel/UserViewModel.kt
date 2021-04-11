package com.mahmouddev.homeworktraining.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mahmouddev.homeworktraining.roomDB.DatabaseHelper
import com.mahmouddev.homeworktraining.roomDB.dbUtil.Resource
import com.mahmouddev.homeworktraining.roomDB.entities.User
import kotlinx.coroutines.launch
import kotlin.Exception

class UserViewModel(var dbHelper: DatabaseHelper) : ViewModel() {
    val TAG = "UserViewModel"
    private val user = MutableLiveData<Resource<User>>()
    private val insertUser = MutableLiveData<Resource<Long>>()


    fun fetchUser(username:String ,password:String) {

        viewModelScope.launch {
            user.postValue(Resource.loading(null))

            try {
             val userData =   dbHelper.getUser(username,password)
                if (userData != null){
                    user.postValue(Resource.success(userData))
                }else{
                    user.postValue(Resource.error("Wrong Username or password", null))
                }
            } catch (ex: Exception) {
                user.postValue(Resource.error(ex.message.toString(), null))
                Log.e(TAG, "fetchUser:${ex.message} ")
            }
        }

    }


    fun insertUser(user: User) {
        viewModelScope.launch {
            insertUser.postValue(Resource.loading(null))
            try {
                val rowId = dbHelper.insertUser(user)
                if (rowId>=1){
                    insertUser.postValue(Resource.success(rowId))
                }else{
                    insertUser.postValue(Resource.error("Something error", null))

                }

            } catch (ex: Exception) {
                insertUser.postValue(Resource.error(ex.message.toString(), null))

                Log.e(TAG, "insertUser: ${ex.message}")
            }
        }
    }



    fun userAsLiveData(): LiveData<Resource<User>> {
        return user
    }

    fun insertAsLiveData(): LiveData<Resource<Long>> {
        return insertUser
    }


}