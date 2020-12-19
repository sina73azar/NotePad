package com.sina.notepadfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.sina.notepadfinal.databinding.ActivityMainBinding
import com.sina.notepadfinal.utils.hideKeyboard
import java.text.DateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
//    fun Long.reformat() {
//        val time = Date().time
//
//    }
}