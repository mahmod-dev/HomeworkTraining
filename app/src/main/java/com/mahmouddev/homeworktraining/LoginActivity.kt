package com.mahmouddev.homeworktraining

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.mahmouddev.homeworktraining.databinding.ActivityLoginBinding
import com.mahmouddev.homeworktraining.roomDB.AppDatabase
import com.mahmouddev.homeworktraining.roomDB.DatabaseImp
import com.mahmouddev.homeworktraining.roomDB.dbUtil.Status
import com.mahmouddev.homeworktraining.roomDB.dbUtil.ViewModelFactory
import com.mahmouddev.homeworktraining.util.MyPreferences
import com.mahmouddev.homeworktraining.viewmodel.UserViewModel

class LoginActivity : AppCompatActivity() {
    lateinit var binding: ActivityLoginBinding
    lateinit var viewModel: UserViewModel
    var isRemember = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initViewModel()
        insertAsLiveData()
        MyPreferences.context =this

            binding.apply {


                btnRegister.setOnClickListener {

                    val name = etName.text.toString()
                    val password = etPassword.text.toString()
                     isRemember = cbIsRemember.isChecked
                    viewModel.fetchUser(name, password)

                }
            }
    }

    private fun initViewModel() {
        val database = AppDatabase.getInstance(this)
        val dbHelper = DatabaseImp(database.daoStudent())
        viewModel =
            ViewModelProvider(this, ViewModelFactory(dbHelper)).get(UserViewModel::class.java)
    }

    private fun insertAsLiveData() {
        viewModel.userAsLiveData().observe(this, {
            when (it.status) {
                Status.LOADING -> {
                    binding.progressBar.visibility = View.VISIBLE
                }
                Status.SUCCESS -> {
                    MyPreferences.setBool("isRemember", isRemember)

                    val intent = Intent(this, MainActivity::class.java)
                    //    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
                    startActivity(intent)
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


}