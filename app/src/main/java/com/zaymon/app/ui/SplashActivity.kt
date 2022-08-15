package com.zaymon.app.ui

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.zaymon.app.PrivacyPolicyActivity
import com.zaymon.app.extesion.startActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        Handler(Looper.getMainLooper()).postDelayed({
            startActivity<PrivacyPolicyActivity> { }
            finish()
        }, 1500)
    }
}