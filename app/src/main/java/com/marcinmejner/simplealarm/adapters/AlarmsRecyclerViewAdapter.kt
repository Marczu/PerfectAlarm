package com.marcinmejner.simplealarm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
import kotlinx.android.synthetic.main.alarms_list_item.view.*


class AlarmsRecyclerViewAdapter(val alarmsList: ArrayList<AlarmEntity>, val context: Context) : RecyclerView.Adapter<AlarmsRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "AlarmsRecyclerViewAdapt"
    

    init {
        Log.d(TAG, "init RV tytuł to: ${alarmsList[1].name}: ")
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.alarm_list_item, parent,false)
        return ViewHolder(view)
    }

        override fun getItemCount(): Int {
        return alarmsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.alarmTitle.text = alarmsList[position]?.name
        holder.alarmTime.text = "${alarmsList[position]?.alarmHours} : ${alarmsList[position]?.alarmMinutes}"
        holder.alarmSnoozeTime.text = alarmsList[position]?.snoozeMinutes.toString()

        Log.d(TAG, "onBindViewHolder:  wewnątrz RV ${alarmsList[position]?.name}")

    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        val alarmItemLayout = view.alarm_item_rel_layout
        val deleteAlarm = view.alarm_item_delete_alarm
        val alarmSwitch = view.alarm_item_switch
        val alarmTime = view.alarm_item_time
        val alarmSnoozeTime = view.alarm_snooze_time_tv
        val alarmTitle = view.alarm_item_title
    }
}