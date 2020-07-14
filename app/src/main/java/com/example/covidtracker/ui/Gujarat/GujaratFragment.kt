package com.example.covidtracker.ui.Gujarat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.covidtracker.R

class GujaratFragment : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_gujarat, container, false)
        val textView: TextView = root.findViewById(R.id.text_gujarat)
        textView.text = "Gujarat"
        return root
        }
}