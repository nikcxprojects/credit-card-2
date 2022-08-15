package com.zaymon.app.api_manager

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class RootModel {
    @SerializedName("success")
    @Expose
    var success: Boolean? = null

    @SerializedName("query")
    @Expose
    var query: Query? = null

    @SerializedName("info")
    @Expose
    var info: Info? = null

    @SerializedName("date")
    @Expose
    var date: String? = null

    @SerializedName("result")
    @Expose
    var result: Double = 0.0

    var oldAmount: Double = 0.0
    var convertedAmount: Double = 0.0
    var toCurrencySymbol = ""
    var fromCurrencySymbol = ""

}


class Query {
    @SerializedName("from")
    @Expose
    var from: String? = null

    @SerializedName("to")
    @Expose
    var to: String? = null

    @SerializedName("amount")
    @Expose
    var amount: Int? = null
}


class Info {
    @SerializedName("timestamp")
    @Expose
    var timestamp: Int? = null

    @SerializedName("rate")
    @Expose
    var rate: Double? = null
}