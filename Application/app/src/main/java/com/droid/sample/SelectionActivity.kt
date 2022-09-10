package com.droid.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.droid.sample.databinding.ActivityMainBinding
import com.droid.sample.normal_service.NormalServiceActivity
import com.droid.sample.utils.extensions.intent.openActivity

class SelectionActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            normalServiceId.setOnClickListener {
                openActivity(NormalServiceActivity::class.java)
            }
        }
    }



}