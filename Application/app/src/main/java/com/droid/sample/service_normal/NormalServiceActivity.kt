package com.droid.sample.service_normal

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droid.sample.databinding.ActivityMainBinding
import com.droid.sample.databinding.ActivityServiceNormalBinding

class NormalServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceNormalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceNormalBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }



}