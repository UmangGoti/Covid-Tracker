package com.example.covidtracker.ui.Otherstate

import android.animation.ValueAnimator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.covidtracker.R
import com.example.covidtracker.StatewiseItem
import kotlinx.android.synthetic.main.data_list.view.*
import kotlinx.android.synthetic.main.fragment_india.*

class OtAdapter(val list: List<StatewiseItem>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context)
            .inflate(R.layout.data_list, parent, false)
        val item = list[position]
        val l1 = item.confirmed.toInt()
        val animator1 = ValueAnimator.ofInt(0, l1)
        animator1.duration = 1000
        animator1.addUpdateListener { animation ->
            view.confirmed_tv.text = animation.animatedValue.toString()
        }
        animator1.start()


        val l2 = item.deaths.toInt()
        val animator2 = ValueAnimator.ofInt(0, l2)
        animator2.duration = 1000
        animator2.addUpdateListener { animation ->
            view.death_tv.text = animation.animatedValue.toString()
        }
        animator2.start()

        val l3 = item.active.toInt()
        val animator3 = ValueAnimator.ofInt(0, l3)
        animator3.duration = 1000
        animator3?.addUpdateListener { animation ->
            view.active_tv.text = animation.animatedValue.toString()
        }
        animator3.start()

        val l4 = item.recovered.toInt()
        val animator4 = ValueAnimator.ofInt(0, l4)
        animator4.duration = 1000
        animator4.addUpdateListener { animation ->
            view.recovered_tv.text = animation.animatedValue.toString()
        }
        animator4.start()

        view.place_Tv.text = item.state
        return view
    }

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size

}