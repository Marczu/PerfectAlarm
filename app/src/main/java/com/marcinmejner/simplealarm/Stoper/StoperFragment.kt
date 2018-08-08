package com.marcinmejner.simplealarm.Stoper


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.marcinmejner.simplealarm.R

class StoperFragment : Fragment() {
    private val TAG = "StoperFragment"

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stoper, container, false)
        return view
    }


}
