package com.example.covidtracker.ui.Otherstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covidtracker.OtClient
import com.example.covidtracker.ResponseOt
import com.example.covidtracker.StatewiseItem
import com.example.covidtracker.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_otherstate.*
import kotlinx.coroutines.*
import java.lang.Exception

class OtherstateFragment : Fragment() {

    lateinit var stateAdapter: OtAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_otherstate, container, false)
        fetchData()
        return root
    }
    private fun fetchData() {
        try {
            GlobalScope.launch {
                val ots = withContext(Dispatchers.IO) {
                    delay(Long.MIN_VALUE)
                    OtClient.api.clone().execute()
                }
                if (ots.isSuccessful) {
                    val data = Gson().fromJson(ots.body?.string(), ResponseOt::class.java)
                    launch(Dispatchers.Main) {
                        bindData(data.statewise.subList(1, data.statewise.size))
                    }
                } else {
                    Toast.makeText(context, "Data Not Found", Toast.LENGTH_LONG).show()
                }
            }
        } catch (e:Exception) {
            e.printStackTrace()
        }
    }
    private fun bindData (sublist: List<StatewiseItem>) {
        stateAdapter = OtAdapter(sublist)
        list.adapter = stateAdapter
    }

}