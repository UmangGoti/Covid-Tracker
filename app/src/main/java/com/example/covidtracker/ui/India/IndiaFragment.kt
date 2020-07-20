package com.example.covidtracker.ui.India

import android.animation.ValueAnimator
import android.os.Bundle
import android.util.TimeUtils
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
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


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
        val dateFormat = SimpleDateFormat("dd/MM/yyyy HH:mm:ss")
        lastUpdated.text = "Last Updated ${getUpdate(dateFormat.parse(data.lastupdatedtime.toString()))}"
    }
    fun getUpdate(lastDT: Date): String {
        val current = Date()
        val hours:Long = TimeUnit.MILLISECONDS.toHours(current.time - lastDT.time)
        val minutes:Long = TimeUnit.MILLISECONDS.toMinutes(current.time - lastDT.time)
        val secs:Long = TimeUnit.MILLISECONDS.toSeconds(current.time - lastDT.time)
        return when {
            secs < 60 -> {
                "Few Secound ago"
            }
            minutes < 60 -> {
                "$minutes Minutes ago"
            }
            hours < 24 -> {
                "$hours Hour ${minutes % 60} Minutes ago"
            }
            else -> {
                SimpleDateFormat("dd/MM/yyyy HH:mm:ss").format(lastDT).toString()
            }
        }
    }
}