package com.example.covidtracker.ui.India

import android.animation.ValueAnimator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.covidtracker.commonClient
import com.example.covidtracker.R
import com.example.covidtracker.ResponseOt
import com.example.covidtracker.StatewiseItem
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_india.*
import kotlinx.coroutines.*


class IndiaFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_india, container, false)
        fetchIndiaResults()
        return root
    }
    private fun fetchIndiaResults() {
        GlobalScope.launch {
            try {
                val ind = withContext(Dispatchers.IO) {
                    delay(Long.MIN_VALUE)
                    commonClient.api.clone().execute()
                }
                if (ind.isSuccessful) {
                    val data = Gson().fromJson(ind.body?.string(), ResponseOt::class.java)
                    launch(Dispatchers.Main) {
                        bindCombinedData(data.statewise[0])
                    }
                } else {
                    Toast.makeText(context, "Data Not Found", Toast.LENGTH_LONG).show()
                }
            } catch (e:Exception) {
                e.printStackTrace()
            }
        }
    }

    private fun bindCombinedData(data: StatewiseItem) {
        var l1 = data.confirmed.toInt()
        val animator1 = ValueAnimator.ofInt(0,l1)
        animator1.duration = 1000
        animator1.addUpdateListener { animation -> totalConfirmedCase.setText(animation.animatedValue.toString())}
        animator1.start()


        var l2 = data.active.toInt()
        val animator2 = ValueAnimator.ofInt(0,l2)
        animator2.duration = 1000
        animator2.addUpdateListener { animation -> totalActiveCase.setText(animation.animatedValue.toString()) }
        animator2.start()

        var l3 = data.recovered.toInt()
        val animator3 = ValueAnimator.ofInt(0,l3)
        animator3.duration = 1000
        animator3.addUpdateListener { animation -> totalRecoveredCase.setText(animation.animatedValue.toString()) }
        animator3.start()

        var l4  = data.deaths.toInt()
        val animator4 = ValueAnimator.ofInt(0,l4)
        animator4.duration = 1000
        animator4.addUpdateListener { animation -> totalDeceasedCase.setText(animation.animatedValue.toString()) }
        animator4.start()

//        totalConfirmedCase.text = data.confirmed
//        totalActiveCase.text = data.active
//        totalRecoveredCase.text = data.recovered
//        totalDeceasedCase.text = data.deaths
        lastUpdated.text = data.lastupdatedtime
    }
}