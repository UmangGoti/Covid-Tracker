package com.example.covidtracker.ui.Gujarat

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.data_list.view.*
import kotlinx.android.synthetic.main.fragment_india.*

class GtAdapter(val dlist: List<DistrictwiseItem>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.data_list, parent, false)
        val item = dlist[position]

        val l1 = item.confirmed
        val animator1 = l1?.let { ValueAnimator.ofInt(0, it) }
        animator1?.duration = 1000
        animator1?.addUpdateListener { animation ->
            view.confirmed_tv.text = animation.animatedValue.toString()
        }
        animator1?.start()

        val l2 = item.deceased
        val animator2 = l2?.let { ValueAnimator.ofInt(0, it) }
        animator2?.duration = 1000
        animator2?.addUpdateListener { animation ->
            view.death_tv.text = animation.animatedValue.toString()
        }
        animator2?.start()

        val l3 = item.active
        val animator3 = l3?.let { ValueAnimator.ofInt(0, it) }
        animator3?.duration = 1000
        animator3?.addUpdateListener { animation ->
            view.active_tv.text = animation.animatedValue.toString()
        }
        animator3?.start()

        val l4 = item.recovered
        val animator4 = l4?.let { ValueAnimator.ofInt(0, it) }
        animator4?.duration = 1000
        animator4?.addUpdateListener { animation ->
            view.recovered_tv.text = animation.animatedValue.toString()
        }
        animator4?.start()

        view.place_Tv.text = item.districtName
        return view
    }

    override fun getItem(position: Int) = dlist[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = dlist.size

}