package com.marcinmejner.simplealarm.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.View
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.alarm.AlarmsViewModel
import com.marcinmejner.simplealarm.model.AlarmEntity
import kotlinx.android.synthetic.main.alarm_list_item.view.*


class AlarmsRecyclerViewAdapter(val alarmsList: ArrayList<AlarmEntity>, val context: Context, val alarmsViewModel: AlarmsViewModel) : RecyclerView.Adapter<AlarmsRecyclerViewAdapter.ViewHolder>() {
    private val TAG = "AlarmsRecyclerViewAdapt"


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(context).inflate(R.layout.alarm_list_item, parent,false)
        return ViewHolder(view)
    }

        override fun getItemCount(): Int {
        return alarmsList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.alarmTitle.text = alarmsList[position]?.name
        holder.alarmTime.text = timeStringFormatting(position)
        holder.alarmSnoozeTime.text = alarmsList[position]?.snoozeMinutes.toString()

        if(alarmsList[position].isAlarmEnabled){
            holder.alarmSwitch.isChecked = true
        }

        isSnoozeEnabled(holder, position)

        deleteSingleAlarm(holder, position)

        Log.d(TAG, "onBindViewHolder:  wewnątrz RV ${alarmsList[position]?.name}")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val alarmItemLayout = view.alarm_item_rel_layout
        val alarmSwitch = view.alarm_item_switch
        val alarmTime = view.alarm_item_time
        val alarmSnoozeTime = view.alarm_snooze_time_tv
        val alarmTitle = view.alarm_item_title
        val snoozeTv = view.snooze_tv
        val deleteSingleAlarm = view.alarm_item_delete_alarm
    }

    /*Check if snooze is enabled, if no then disable snooze view*/
    fun isSnoozeEnabled(holder: ViewHolder, position: Int){
        if(alarmsList[position].isSnoozeEnabled){
            holder.snoozeTv.visibility = View.GONE
            holder.alarmSnoozeTime.visibility = View.GONE
        }
    }

    fun deleteSingleAlarm(holder: ViewHolder, position: Int){
        holder.deleteSingleAlarm.setOnClickListener {
            alarmsViewModel.deleteSingleAlarmById(alarmsList[position].id)
        }
    }

    /*Check if minutes or hours are <10 and add 0*/
    fun timeStringFormatting(position: Int): String{
        val hours = alarmsList[position].alarmHours?.toInt()
        val minutes = alarmsList[position].alarmMinutes?.toInt()

        val hoursAfter = if(alarmsList[position].alarmHours?.toInt()!! < 10){
            "0$hours"
        }else{
            "$hours"
        }

        val minutesAfter = if(alarmsList[position].alarmMinutes?.toInt()!! < 10){
            "0$minutes"
        }else{
            "$minutes"
        }

        return "$hoursAfter:$minutesAfter"
    }
}