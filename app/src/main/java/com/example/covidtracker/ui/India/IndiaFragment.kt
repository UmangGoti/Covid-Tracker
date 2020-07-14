package com.example.covidtracker.ui.India

import android.app.DownloadManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covidtracker.R
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class IndiaFragment : Fragment() {

//    var url:URL = URL("https://api.covid19india.org/data.json")
//    var jsonString:String = ""
//    var textVal:String = ""

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_india, container, false)

        val textView: TextView = root.findViewById(R.id.text_india)
        textView.text = "India"
//        jsonString = getJsonFromUrl(url.toString())

//        try{
//            var jsonOb1:JSONObject = JSONObject(url.toString())
//            var jsonArr1:JSONArray = jsonOb1.getJSONArray("tested")
//            var jsonArr2:JSONArray = JSONArray()
//            for (i in 0 until jsonArr1.length()){
//                var jsonOb2:JSONObject = jsonArr1.getJSONObject(i)
//                if(jsonOb2.getString("updatetimestamp").equals("14/07/2020 09:00:00")){
//                    textVal = jsonOb2.getString("totalsamplestested")
//                }
//            }
//            val textView1: TextView = root.findViewById(R.id.totalCase)
//            textView1.text = textVal
//        }catch(e: Exception){
//            e.printStackTrace()
//        }
        return root
    }
//    fun getJsonFromUrl(pURL: String) : String {
//        return URL(pURL).readText()
//    }
}