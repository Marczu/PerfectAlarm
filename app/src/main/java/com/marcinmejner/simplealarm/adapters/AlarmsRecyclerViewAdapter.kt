package com.marcinmejner.simplealarm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.R
import kotlinx.android.synthetic.main.alarms_list_item.view.*

class AlarmsRecyclerViewAdapter(val alarmsList: ArrayList<AlarmEntity>, val context: Context) : RecyclerView.Adapter<AlarmsRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "AlarmsRecyclerViewAdapt"

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.alarms_list_item, parent, false)
        return ViewHolder(view)
    }

        override fun getItemCount(): Int {
        return alarmsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {


    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val alarmItemLayout = view.alarm_item_rel_layout
        val deleteAlarm = view.alarm_item_delete_alarm
        val alarmSwitch = view.alarm_item_switch
        val alarmTime = view.alarm_item_time
        val alarmSnoozeTime = view.alarm_snooze_time_tv
    }
}