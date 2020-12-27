package com.sina.notepadfinal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.sina.notepadfinal.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val inflater = layoutInflater
        val binding: ActivityMainBinding = ActivityMainBinding.inflate(inflater)

        setContentView(binding.root)
        setSupportActionBar(binding.myToolbar)
        // Log.e("TAG", "onCreate: what is going on? ")
    }

}