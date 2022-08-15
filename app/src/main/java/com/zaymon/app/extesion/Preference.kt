package com.zaymon.app.extesion

import android.content.Context
import com.google.gson.Gson
import com.zaymon.app.api_manager.RootModel

fun Context.setLastCurrency(value: RootModel) {
    val sharedPreferences = this.getSharedPreferences("currency_app_preference", 0)
    sharedPreferences.edit().putString("currency_app_preference_last", Gson().toJson(value)).apply()
}

fun Context.getLastCurrency(): RootModel? {
    val sharedPreferences = this.getSharedPreferences("currency_app_preference", 0)
    if (!sharedPreferences.getString("currency_app_preference_last", "").equals("")) {
        return Gson().fromJson(
            sharedPreferences.getString("currency_app_preference_last", "") ?: "",
            RootModel::class.java
        )
    }
    return null
}
