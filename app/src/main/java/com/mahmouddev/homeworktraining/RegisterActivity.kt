package com.mahmouddev.homeworktraining

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.homeworktraining.databinding.ActivityRegisterBinding
import com.mahmouddev.homeworktraining.roomDB.AppDatabase
import com.mahmouddev.homeworktraining.roomDB.DatabaseImp
import com.mahmouddev.homeworktraining.roomDB.dbUtil.Status
import com.mahmouddev.homeworktraining.roomDB.dbUtil.ViewModelFactory
import com.mahmouddev.homeworktraining.roomDB.entities.User
import com.mahmouddev.homeworktraining.util.MyPreferences
import com.mahmouddev.homeworktraining.viewmodel.UserViewModel

class RegisterActivity : AppCompatActivity() {
    lateinit var binding: ActivityRegisterBinding
    lateinit var viewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        insertAsLiveData()
        checkIsRemember()


        binding.apply {

            btnRegister.setOnClickListener {

                val name = etName.text.toString()
                val age = etAge.text.toString()
                val password = etPassword.text.toString()

                viewModel.insertUser(User(name,age,password))


            }

            btnLogin.setOnClickListener {
                startActivity(Intent(this@RegisterActivity, LoginActivity::class.java))
                finish()

            }
        }
    }


    private fun initViewModel() {
        val database = AppDatabase.getInstance(this)
        val dbHelper = DatabaseImp(database.daoStudent())
        viewModel = ViewModelProvider(this, ViewModelFactory(dbHelper)).get(UserViewModel::class.java)
    }

    private fun insertAsLiveData() {
        viewModel.insertAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    startActivity(Intent(this, LoginActivity::class.java))
                    finish()
                    binding.progressBar.visibility = View.GONE

                }
                Status.ERROR -> {
                    Toast.makeText(this, it.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE
                }
            }

        })
    }

    private fun checkIsRemember(){
        MyPreferences.context = this
        val prefIsRemember = MyPreferences.getBool("isRemember")
        if (prefIsRemember){
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish()
            return
        }
    }

}