package com.droid.sample.intent_service

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droid.sample.databinding.ActivityServiceIntentBinding
import com.droid.sample.databinding.ActivityServiceNormalBinding
import com.droid.sample.intent_service.services.INTENT_COUNTER_KEY
import com.droid.sample.intent_service.services.IntentDownloadService
import com.droid.sample.normal_service.services.NormalDownloadService
import com.droid.sample.utils.extensions.activity.isMyServiceRunning
import com.droid.sample.utils.extensions.toast.toast

class IntentServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceIntentBinding

    private var count : Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceIntentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startServiceId.setOnClickListener {
                val intent = Intent(this@IntentServiceActivity, IntentDownloadService::class.java)
                intent.putExtra(INTENT_COUNTER_KEY, count);
                startService(intent)
                count++
            }
        }

    }


}