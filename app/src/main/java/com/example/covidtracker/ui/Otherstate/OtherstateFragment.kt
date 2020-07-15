package com.example.covidtracker.ui.Otherstate

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covidtracker.*
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_otherstate.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

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
    private fun fetchData(){
        GlobalScope.launch {
            val response = withContext(Dispatchers.IO) {
                OtClient.api.clone().execute()
            }
            if (response.isSuccessful) {
                val data = Gson().fromJson(response.body?.string(), ResponseOt::class.java)
                launch(Dispatchers.Main) {
                    bindData(data.statewise.subList(1,data.statewise.size))
                }
            }
            else{
                Toast.makeText(context,"Not Found", Toast.LENGTH_LONG).show()
            }
        }
    }
    private fun bindData(sublist: List<StatewiseItem>){
        stateAdapter = OtAdapter(sublist)
        list.adapter = stateAdapter
    }

}