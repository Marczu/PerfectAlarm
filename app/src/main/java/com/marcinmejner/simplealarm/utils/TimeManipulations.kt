package com.marcinmejner.simplealarm.utils

import android.util.Log
import java.text.SimpleDateFormat
import java.util.*


class TimeManipulations {
    private val TAG = "TimeManipulations"

    val calendar = Calendar.getInstance()
    val calendar2 = Calendar.getInstance()

    fun timeTomorrow(): String {
        calendar.timeZone = TimeZone.getTimeZone("Europe/Warsaw")

        calendar.time
        calendar2.time
        Log.d(TAG, "timeTomorrow: current date data: ${calendar.time}")

        calendar.add(Calendar.DAY_OF_WEEK, 1)
        val weekdayPlusOne = calendar.time

        val sdf = SimpleDateFormat("EEEE")
        val tomorowDate = sdf.format(weekdayPlusOne)
        val today = sdf.format(calendar2.time)

        Log.d(TAG, "getTime: $tomorowDate i $today")

        return tomorowDate
    }

    fun addSnoozeTime(addedMinutes: Int): Int {
        calendar.timeZone = TimeZone.getTimeZone("Europe/Warsaw")
        calendar.time
        calendar.add(Calendar.MINUTE, addedMinutes)
        Log.d(TAG, "addSnoozeTime: minuty to : ${calendar.get(Calendar.MINUTE)}")
        return calendar.get(Calendar.MINUTE)
    }
}