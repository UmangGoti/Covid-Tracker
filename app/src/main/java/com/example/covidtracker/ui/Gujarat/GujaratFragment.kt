package com.example.covidtracker.ui.Gujarat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.Volley
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.fragment_otherstate.*
import org.json.JSONArray
import org.json.JSONObject


class GujaratFragment : Fragment() {
//    var arrayList: kotlin.collections.List<DistModel>? = null
    var remind_list:ArrayList<HashMap<String, String>> = ArrayList()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gujarat, container, false)
        getDistrictData()
        return root
    }
    private fun getDistrictData() {
        val url:String = "https://api.covid19india.org/state_district_wise.json"
        //using volley make request
        val request = JsonObjectRequest(Request.Method.GET, url, null,Response.Listener<JSONObject> {
            response -> try {
            // gujarat state object
            var jobState:JSONObject = response.getJSONObject("Gujarat")
            //gujarat to districtdata
            var jobDistrict:JSONObject = jobState.getJSONObject("districtData")
            //key as value which is district data
            var key:JSONArray = jobDistrict.names()
            for (i in 0 until key.length()) {

                var districtName:String = key.getString(i)

                var jobList:JSONObject = jobDistrict.getJSONObject(districtName)

                var active_case:String = jobList.getString("active")
                var confirmed_case:String = jobList.getString("confirmed")
                var deceased_case:String = jobList.getString("deceased")
                var recovered_case:String = jobList.getString("recovered")
                var state:String = "Gujarat"

                val map: HashMap<String, String> = HashMap()
                map["state_name"] = state
                map["confirm_cases"] = confirmed_case
                map["active_cases"] = active_case
                map["recovered_cases"] = recovered_case
                map["deceased_cases"] = deceased_case
                map["dist_name"] = districtName
                //hashmap to arraylist
                remind_list.add(map)
            }


        }  catch (e:Exception) {
            e.printStackTrace()
        }

        },
        Response.ErrorListener { error ->  
            // here pass toast for error 
        })
    }
}



