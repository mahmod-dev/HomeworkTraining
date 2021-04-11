package com.mahmouddev.homeworktraining

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.mahmouddev.homeworktraining.databinding.ActivityMainBinding
//https://sqlitestudio-for-mac.peatix.com/
class MainActivity : AppCompatActivity() {
    val TAG = "MainActivity"
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}