package com.example.covidtracker

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request

object OtClient {
    private val otClient = OkHttpClient()
    private val request = Request.Builder()
        .url("https://api.covid19india.org/data.json")
        .build()
    val api = otClient.newCall(request)
}