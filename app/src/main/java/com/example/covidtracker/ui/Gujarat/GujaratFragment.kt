package com.example.covidtracker.ui.Gujarat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.covidtracker.commonClient
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_gujarat.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONObject


class GujaratFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gujarat, container, false)
        getDistrictData("Gujarat")
        return root
    }
    private fun getDistrictData(stateName:String) {
        GlobalScope.launch {
        val gdr = withContext(Dispatchers.IO) { commonClient.district_api.clone().execute() }
        if (gdr.isSuccessful) {
            val jsonObject = JSONObject(gdr.body?.string())
            val data = DistrictDataModel().fromJson(jsonObject,  stateName).getDistrictWise()
            Log.i("info print data","$data")
            launch(Dispatchers.Main){
                bindDistrictWiseData(data)
            }
        }
    }
    }

    private fun bindDistrictWiseData(subList: List<DistrictwiseItem>) {
        var districtAdapter = GtAdapter(subList)
        list_gujarat.adapter = districtAdapter
    }
}



