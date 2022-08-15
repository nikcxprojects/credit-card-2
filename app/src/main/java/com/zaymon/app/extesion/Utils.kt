package com.zaymon.app.extesion

import android.content.Context
import android.net.ConnectivityManager


fun Context.isNetworkAvailable( ): Boolean {
    return ((getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo != null
            && (getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager).activeNetworkInfo!!.isConnectedOrConnecting)
}