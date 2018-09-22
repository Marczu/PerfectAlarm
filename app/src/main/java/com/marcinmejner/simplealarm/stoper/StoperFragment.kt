package com.marcinmejner.simplealarm.stoper


import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.os.CountDownTimer
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.utils.TimerState
import com.marcinmejner.simplealarm.viewModels.StoperViewModel

class StoperFragment : Fragment() {
    private val TAG = "StoperFragment"

    private lateinit var stoperViewModel: StoperViewModel
    private lateinit var timer: CountDownTimer
    private var timerLenghtSeconds: Long = 0L
    private var secondsRemaining: Long = 0L
    private var timerState = TimerState.Stopped


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view = inflater.inflate(R.layout.fragment_stoper, container, false)

        initViewModel()

        return view
    }

    private fun initViewModel() {
        stoperViewModel = ViewModelProviders.of(this)
                .get(StoperViewModel::class.java)
    }


}
