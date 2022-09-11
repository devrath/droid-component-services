package com.droid.sample

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.droid.sample.databinding.ActivityMainBinding
import com.droid.sample.foreground_service.ForegroundServiceActivity
import com.droid.sample.intent_service.IntentServiceActivity
import com.droid.sample.job_intent_service.JobIntentServiceActivity
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
            intentServiceId.setOnClickListener {
                openActivity(IntentServiceActivity::class.java)
            }
            jobIntentServiceId.setOnClickListener {
                openActivity(JobIntentServiceActivity::class.java)
            }
            foregroundServiceId.setOnClickListener {
                openActivity(ForegroundServiceActivity::class.java)
            }
        }
    }


}