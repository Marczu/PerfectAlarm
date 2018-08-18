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
        holder.daysOfWeek.text = alarmsList[position]?.daysOfWeek.toString()

        holder.alarmSwitch.isChecked = alarmsList[position].isAlarmEnabled

        alarmSwitch(holder, position)

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
        val daysOfWeek = view.alarm_item_tv_days_of_week
        val daysOfWeekIv = view.iv_days_of_week
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

    fun updateIsAlarmOn(isAlarmOn: Boolean, position: Int){
        alarmsViewModel.updateIsAlarmEnabled(isAlarmOn, alarmsList[position].id)
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

    fun alarmSwitch(holder: ViewHolder, position: Int){
        holder.alarmSwitch.setOnCheckedChangeListener { buttonView, isChecked ->
            if(isChecked) {
                updateIsAlarmOn(true, position)
                Log.d(TAG, "onBindViewHolder: switch ON: ${alarmsList[position].isAlarmEnabled}")
            }
            else {
                updateIsAlarmOn(false, position)
                Log.d(TAG, "onBindViewHolder: switch ON: ${alarmsList[position].isAlarmEnabled}")
            }
        }

        /*Set opacity of layout when alarm is turned off*/
        if(alarmsList[position].isAlarmEnabled) {
            holder.alarmItemLayout.background.alpha = 255
            holder.alarmTime.alpha = 1.0f
            holder.alarmTitle.alpha = 1.0f
            holder.alarmSnoozeTime.alpha = 1.0f
            holder.daysOfWeek.alpha = 1.0f
            holder.daysOfWeekIv.alpha = 1.0f
        }
        else {
            holder.alarmItemLayout.background.alpha = 128
            holder.alarmTime.alpha = 0.5f
            holder.alarmTitle.alpha = 0.5f
            holder.alarmSnoozeTime.alpha = 0.5f
            holder.daysOfWeek.alpha = 0.5f
            holder.daysOfWeekIv.alpha = 0.5f

        }
    }
}