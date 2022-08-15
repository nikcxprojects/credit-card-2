package com.zaymon.app.api_manager

import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.HeaderMap
import retrofit2.http.QueryMap
import retrofit2.http.Url


interface APIInterface {

    @GET
    fun convertAmount(
        @Url url: String?,
        @HeaderMap hashMap: HashMap<String, String>,
        @QueryMap queryMap: HashMap<String, String>
    ): Call<RootModel>

}