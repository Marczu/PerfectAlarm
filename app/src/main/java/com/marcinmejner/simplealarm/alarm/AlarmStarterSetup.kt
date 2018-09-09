package com.marcinmejner.simplealarm.alarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.broadcast.MyBroadcastReciver
import com.marcinmejner.simplealarm.model.AlarmEntity
import java.util.*

class AlarmStarterSetup {
    private val TAG = "AlarmStarterSetup"

    //vars
    var currentAlarms: MutableList<AlarmEntity> = ArrayList()
    lateinit var alarmManager: AlarmManager
    lateinit var pi:PendingIntent
    var isTommorow: Boolean = false

    /*search for turned on alarms and add it to list*/
    fun getTurnedOnAlarms(turnedOnAlarms: List<AlarmEntity>, context: Context) {
        val turnedOnAlarmsList = ArrayList<AlarmEntity>()
        turnedOnAlarmsList.clear()

        turnedOnAlarms.forEach {
            if(it.isAlarmEnabled){
                turnedOnAlarmsList.add(it)
            }
        }

        currentAlarms.clear()
        turnedOnAlarmsList.forEach {
            currentAlarms.add(it)
        }

        setAlarm(context)
        Log.d(TAG, "getCurrentAlarms: ${currentAlarms[0].name}")
    }

    fun setAlarm(context: Context) {

        val hour = currentAlarms[0].alarmHours?.toInt()
        val minute = currentAlarms[0].alarmMinutes?.toInt()
        Log.d(TAG, "setAlarm: hour: $hour")
        Log.d(TAG, "setAlarm: minute: $minute")

        val calendar = Calendar.getInstance().apply {
            set(Calendar.HOUR_OF_DAY, hour!!)
            set(Calendar.MINUTE, minute!!)
            set(Calendar.SECOND, 0)
        }

        var startUpTime = calendar.timeInMillis
        if (System.currentTimeMillis() > startUpTime) {
            startUpTime += 24 * 60 * 60 * 1000
            isTommorow = true
        }else{
            isTommorow = false
        }

        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent = Intent(context, MyBroadcastReciver::class.java)
        intent.putExtra(context.getString(R.string.intent_message), "alarm time")
        intent.action = "com.marcinmejner.alarmmanager"

        pi = PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startUpTime,
                AlarmManager.INTERVAL_DAY, pi)
    }

}