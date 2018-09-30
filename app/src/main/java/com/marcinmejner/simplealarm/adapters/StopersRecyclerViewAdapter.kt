package com.marcinmejner.simplealarm.adapters

import android.content.Context
import android.os.CountDownTimer
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.R.id.*
import com.marcinmejner.simplealarm.model.StoperEntity
import com.marcinmejner.simplealarm.utils.TimerState
import com.marcinmejner.simplealarm.viewModels.StoperViewModel
import kotlinx.android.synthetic.main.stoper_list_item.*
import kotlinx.android.synthetic.main.stoper_list_item.view.*


class StopersRecyclerViewAdapter(val stoperList: ArrayList<StoperEntity>, val context: Context, val stoperViewModel: StoperViewModel)
    : RecyclerView.Adapter<StopersRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "StopersRecyclerViewAdap"

    private lateinit var timer: CountDownTimer


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.stoper_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stoperList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.startBtn.setOnClickListener {
            startButton(holder, position)
        }

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stoperLayout = view.stoper_item_rel_layout
        val startBtn = view.btn_stoper_start
        val stopBtn = view.btn_stoper_stop
        val pauseBtn = view.btn_stoper_pause
        val stoperCountdown = view.stoper_countdown
        val progresCountdown = view.stoper_progress_countdown
    }

    private fun startButton(holder: ViewHolder, position: Int) {
//        btn_stoper_start.setOnClickListener {
//            timerState = TimerState.Running
//            startTimer(holder, position)
    }

    private fun startTimer(holder: StopersRecyclerViewAdapter.ViewHolder, position: Int) {
//        timer = object : CountDownTimer(secondsRemaining * 1000, 1000) {
//            override fun onFinish() = onTimerFinished()

//            override fun onTick(millisUntilFinished: Long) {
////                secondsRemaining = millisUntilFinished / 1000
////                updateCountdownUI()
//            }
//        }.start()
    }

    private fun pauseButton(holder: StopersRecyclerViewAdapter.ViewHolder, position: Int) {
//        btn_stoper_pause.setOnClickListener {
//            timer.cancel()
//            timerState = TimerState.Paused
    }


    private fun stopButton(holder: StopersRecyclerViewAdapter.ViewHolder, position: Int) {
//        btn_stoper_stop.setOnClickListener {
//            timer.cancel()
//            onTimerFinish()
    }


    private fun onTimerFinish(holder: StopersRecyclerViewAdapter.ViewHolder, position: Int) {
//        timerState = TimerState.Stopped
//
//        setNewTimerLength()
//
//        stoper_progress_countdown.progress = 0
    }

}




