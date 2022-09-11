package com.droid.sample.job_intent_service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droid.sample.databinding.ActivityJobIntentServiceBinding
import com.droid.sample.databinding.ActivityServiceNormalBinding
import com.droid.sample.intent_service.services.INTENT_COUNTER_KEY
import com.droid.sample.intent_service.services.IntentDownloadService
import com.droid.sample.job_intent_service.services.MyJobIntentService

class JobIntentServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityJobIntentServiceBinding

    private var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityJobIntentServiceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startServiceId.setOnClickListener {
                val intent = Intent()
                intent.putExtra(INTENT_COUNTER_KEY, count);
                MyJobIntentService.startWork(this@JobIntentServiceActivity, intent)
                count++
            }
        }

    }


}