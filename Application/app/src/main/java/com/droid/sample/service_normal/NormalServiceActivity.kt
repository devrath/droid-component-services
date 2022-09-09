package com.droid.sample.service_normal

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.droid.sample.databinding.ActivityServiceNormalBinding
import com.droid.sample.service_normal.services.NormalDownloadService
import com.droid.sample.utils.extensions.toast.toast

class NormalServiceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityServiceNormalBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityServiceNormalBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.apply {
            startServiceId.setOnClickListener {
                val intent = Intent(this@NormalServiceActivity, NormalDownloadService::class.java)
                startService(intent)
            }

            checkServiceId.setOnClickListener {
                if (NormalDownloadService.isCurrentServiceRunning()) {
                    toast(
                        msg = "Service is running",
                        isShort = true,
                        context = this@NormalServiceActivity
                    )
                } else {
                    toast(
                        msg = "Service is not running",
                        isShort = true,
                        context = this@NormalServiceActivity
                    )
                }
            }

            stopServiceId.setOnClickListener {
                NormalDownloadService.stopService()
            }

        }
    }
}