package com.marcinmejner.simplealarm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.StoperEntity
import com.marcinmejner.simplealarm.viewModels.StoperViewModel
import kotlinx.android.synthetic.main.stoper_list_item.view.*


class StopersRecyclerViewAdapter(val stoperList: ArrayList<StoperEntity>, val context: Context, val stoperViewModel: StoperViewModel)
    : RecyclerView.Adapter<StopersRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "StopersRecyclerViewAdap"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.stoper_list_item, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return stoperList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        holder.stoperCountdown.text = stoperList[position].alarmCountDown
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val stoperLayout = view.stoper_item_rel_layout
        val startBtn = view.btn_stoper_start
        val stopBtn = view.btn_stoper_stop
        val pauseBtn = view.btn_stoper_pause
        val stoperCountdown = view.stoper_countdown
        val progresCountdown = view.stoper_progress_countdown
    }


}