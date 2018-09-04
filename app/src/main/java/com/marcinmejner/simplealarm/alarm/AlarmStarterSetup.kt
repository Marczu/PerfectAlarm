package com.marcinmejner.simplealarm.alarm

import android.util.Log
import com.marcinmejner.simplealarm.model.AlarmEntity

class AlarmStarterSetup {
    private val TAG = "AlarmStarterSetup"

    //vars
    var currentAlarms: MutableList<AlarmEntity> = ArrayList()


    fun getCurrentAlarms(currentAlarmsList: List<AlarmEntity>){
        currentAlarms.clear()
        currentAlarmsList.forEach {
            currentAlarms.add(it)
        }
        Log.d(TAG, "getCurrentAlarms: ${currentAlarms[0].name}")
        Log.d(TAG, "getCurrentAlarms: ${currentAlarms[1].name}")
        Log.d(TAG, "getCurrentAlarms: ${currentAlarms[2].name}")
    }

}