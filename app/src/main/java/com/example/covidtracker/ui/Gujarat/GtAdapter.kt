package com.example.covidtracker.ui.Gujarat

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.covidtracker.R
import kotlinx.android.synthetic.main.data_list.view.*

class GtAdapter (val dlist: List<DistrictwiseItem>) : BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view = convertView ?: LayoutInflater.from(parent?.context).inflate(R.layout.data_list,parent,false)
        val item = dlist[position]
        view.confirmed_tv.text = item.confirmed.toString()
        view.active_tv.text = item.active.toString()
        view.death_tv.text = item.deceased.toString()
        view.recovered_tv.text = item.recovered.toString()
        view.place_Tv.text = item.districtName
        return view
    }

    override fun getItem(position: Int) = dlist[position]

    override fun getItemId(position: Int) = position.toLong()

    override fun getCount() = dlist.size

}