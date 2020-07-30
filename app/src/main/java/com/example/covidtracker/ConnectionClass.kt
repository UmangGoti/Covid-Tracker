package com.example.covidtracker

import android.app.Dialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.view.WindowManager
import android.widget.Button

class ConnectionClass(context: Context) {
    val contextMain = context
    val connectivityManager: ConnectivityManager =
        context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = connectivityManager.activeNetworkInfo


    fun checkConnection() : Boolean{
        if (activeNetwork == null || !activeNetwork.isConnected || !activeNetwork.isAvailable) {
            return true
        } else {
            return false
        }
    }
}