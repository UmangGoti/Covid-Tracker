package com.example.covidtracker.ui.Otherstate

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.covidtracker.R
import com.example.covidtracker.StatewiseItem
import kotlinx.android.synthetic.main.gujarat_list.view.*

class OtAdapter (val list: List<StatewiseItem>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
    val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.gujarat_list,parent,false)
        val item = list[position]
        view.confirmed_tv.text = item.confirmed
        view.recovered_tv.text = item.recovered
        view.active_tv.text = item.active
        view.death_tv.text = item.deaths
        view.place_Tv.text = item.state
        return view
    }

    override fun getItem(position: Int) = list[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = list.size

}