package com.droid.sample.job_intent_service

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droid.sample.databinding.ActivityJobIntentServiceBinding
import com.droid.sample.databinding.ActivityServiceNormalBinding

class JobIntentServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobIntentServiceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }


}