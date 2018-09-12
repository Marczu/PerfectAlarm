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
    lateinit var pi: PendingIntent
    var requestCode: Int = 0
    var pendingIntentsList = mutableListOf<PendingIntent>()

    /*search for turned on alarms and add it to list*/
    fun getTurnedOnAlarms(turnedOnAlarms: List<AlarmEntity>, context: Context) {
        val turnedOnAlarmsList = ArrayList<AlarmEntity>()
        turnedOnAlarmsList.clear()

        turnedOnAlarms.forEach {
            if (it.isAlarmEnabled) {
                turnedOnAlarmsList.add(it)
            }
        }

        currentAlarms.clear()
        turnedOnAlarmsList.forEach {
            currentAlarms.add(it)
        }

        alarmsStarter(context)
    }

    /*  Iterate all alarm and set on with days alarm will repeat*/
    fun alarmsStarter(context: Context) {
        alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        if (pendingIntentsList.isNotEmpty())
            pendingIntentsList.forEach { alarmManager.cancel(it) }

        currentAlarms.forEach { alarm ->
            if (alarm.mondayCheck) setAlarm(context, 2, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
            if (alarm.tuesdayCheck) setAlarm(context, 3, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
            if (alarm.wednesdayCheck) setAlarm(context, 4, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
            if (alarm.thursdayCheck) setAlarm(context, 5, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
            if (alarm.fridayCheck) setAlarm(context, 6, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
            if (alarm.saturdayCheck) setAlarm(context, 7, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
            if (alarm.sundayCheck) setAlarm(context, 1, alarm.alarmHours!!.toInt(), alarm.alarmMinutes!!.toInt(), alarm.id)
        }
    }

    /*Set hour, minutes, days on with alarm will start, and let alarm manager send pending intent to broadcastReciver   */
    fun setAlarm(context: Context, weekday: Int, hours: Int, minutes: Int, id: Int) {
        val hour = hours
        val minute = minutes
        Log.d(TAG, "setAlarm: z room: godzina $hour minuta $minute")

        val calendar = Calendar.getInstance().apply {
            set(Calendar.DAY_OF_WEEK, weekday)
            set(Calendar.HOUR_OF_DAY, hour)
            set(Calendar.MINUTE, minute)
            set(Calendar.SECOND, 15)
        }
        var startUpTime = calendar.timeInMillis
        if (System.currentTimeMillis() > startUpTime) {
            startUpTime += 7 * 24 * 60 * 60 * 1000
        }


        val calendarTest = Calendar.getInstance()
        calendarTest.timeInMillis = startUpTime

        Log.d(TAG, "setAlarm ALARM WILL START:  Minute: ${calendarTest.get(Calendar.MINUTE)} : " +
                "Hour: ${calendarTest.get(Calendar.HOUR_OF_DAY)} : " +
                "Day of month: ${calendarTest.get(Calendar.DAY_OF_MONTH)}")

        val intent = Intent(context, MyBroadcastReciver::class.java)
        intent.putExtra(context.getString(R.string.id), id)
        intent.action = "com.marcinmejner.alarmmanager"

        pi = PendingIntent.getBroadcast(context, requestCode, intent, PendingIntent.FLAG_UPDATE_CURRENT)
        requestCode++
        pendingIntentsList.add(pi)

        alarmManager.setRepeating(AlarmManager.RTC_WAKEUP, startUpTime,
                AlarmManager.INTERVAL_DAY * 7, pi)
    }
}