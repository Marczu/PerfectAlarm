package com.marcinmejner.simplealarm.Alarm

import android.arch.lifecycle.ViewModelProviders
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.marcinmejner.simplealarm.R

class AlarmsFragment : Fragment() {
    private val TAG = "AlarmsFragment"

    lateinit var alarmsViewModel: AlarmsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_alarms, container, false)
        initViewModel()


        return view
    }

    private fun initViewModel() {
        alarmsViewModel = ViewModelProviders.of(this)
                .get(AlarmsViewModel::class.java)
    }


}
