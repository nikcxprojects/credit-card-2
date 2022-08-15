package com.zaymon.app

import android.content.ActivityNotFoundException
import android.content.Intent
import android.net.Uri
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.zaymon.app.extesion.Constant
import com.zaymon.app.extesion.startActivity
import com.zaymon.app.ui.ConverterActivity


class PrivacyPolicyActivity : AppCompatActivity(R.layout.activity_privacy_policy) {
    fun onClick(view: View) {
        when (view.id) {
            R.id.btnAcceptPrivacyPolicy -> startActivity<ConverterActivity> { }
            R.id.tvPrivacyPolicy -> {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(Constant.PRIVACY_POLICY_URL))
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
                intent.setPackage("com.android.chrome")
                try {
                    startActivity(intent)
                } catch (ex: ActivityNotFoundException) {
                    intent.setPackage("com.amazon.cloud9")
                    startActivity(intent)
                }
            }
        }
    }
}