package com.example.covidtracker

import okhttp3.OkHttp
import okhttp3.OkHttpClient
import okhttp3.Request

object commonClient {
    private val otClient = OkHttpClient()
    private val request = Request.Builder()
        .url("https://api.covid19india.org/data.json")
        .build()
    private val district_request = Request.Builder()
        .url("https://api.covid19india.org/state_district_wise.json")
        .build()
    val api = otClient.newCall(request)
    val district_api = otClient.newCall(district_request)
}