package com.marcinmejner.simplealarm.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.support.v4.content.ContextCompat.startActivity
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.alarm.AlarmRingingScreen
import com.marcinmejner.simplealarm.alarm.AlarmStarterSetup
import java.time.DayOfWeek
import java.util.*

class MyBroadcastReciver : BroadcastReceiver() {
    private val TAG = "MyBroadcastReciver"

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: inside onRecive")

        if (intent?.action.equals("com.marcinmejner.alarmmanager")) {
            var extras = intent?.extras
            val id = extras?.getInt("id")
            val weekday = extras?.getInt("day_of_week")
            val timeInMilis = extras?.getLong("time_in_millis")
            val startToday = extras?.getBoolean("start_today")
            Log.d(TAG, "onReceive: alarm started, id: $id, weekday: $weekday")
            initAlarmActivity(context, id, weekday, startToday)
        } else if (intent?.action.equals("android.permission.RECEIVE_BOOT_COMPLETED")) {
            val alarmStarterSetup = AlarmStarterSetup()
            alarmStarterSetup.alarmsStarter(context!!)
        }
    }

    /*Go to AlarmRingingScreen*/
    private fun initAlarmActivity(context: Context?, id: Int?, weekday: Int?, startToday: Boolean?) {
//        if (canActivateAlarm(weekday, startToday)) {
            Intent(context, AlarmRingingScreen::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                action = "android.intent.action.VIEW"
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                action = Intent.ACTION_MAIN
                addCategory(Intent.CATEGORY_LAUNCHER)
                putExtra(context?.getString(R.string.alarm_ring_id), id)
                startActivity(context!!, this, null)
            }
//        }
    }

    private fun canActivateAlarm(weekday: Int?, startToday: Boolean?): Boolean {
        val calendar = Calendar.getInstance()
        if (calendar.get(Calendar.DAY_OF_WEEK) == weekday && startToday!!) {
            return true
        }
        return false
    }
}