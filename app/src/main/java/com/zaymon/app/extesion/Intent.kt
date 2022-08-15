package com.zaymon.app.extesion

import android.app.Activity
import android.content.Context
import android.content.Intent

inline fun <reified T> Context.startActivity(block: Intent.() -> Unit) {
    Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        block()
        startActivity(this)
    }
}

inline fun <reified T> Activity.startActivityForResult(requestCode: Int, block: Intent.() -> Unit) {
    Intent(this, T::class.java).apply {
        addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT)
        block()
        startActivityForResult(this, requestCode)
    }
}