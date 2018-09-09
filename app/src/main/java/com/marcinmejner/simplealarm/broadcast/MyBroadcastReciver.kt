package com.marcinmejner.simplealarm.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import android.support.v4.content.ContextCompat.startActivity
import com.marcinmejner.simplealarm.alarm.AlarmRingingScreen
import com.marcinmejner.simplealarm.alarm.AlarmStarterSetup

class MyBroadcastReciver: BroadcastReceiver(){
    private val TAG = "MyBroadcastReciver"

    override fun onReceive(context: Context?, intent: Intent?) {

        if(intent?.action.equals("com.marcinmejner.alarmmanager")){
            var b = intent?.extras
            Log.d(TAG, "onReceive: alarm started")
            initAlarmActivity(context)
        }
        else if(intent?.action.equals("android.permission.RECEIVE_BOOT_COMPLETED")){
            val alarmStarterSetup = AlarmStarterSetup()
            alarmStarterSetup.setAlarm(context!!)
        }
    }

    /*Go to AlarmRingingScreen*/
    fun initAlarmActivity(context: Context?){
        Intent(context, AlarmRingingScreen::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
            action = "android.intent.action.VIEW"
            addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
            action = Intent.ACTION_MAIN
            addCategory(Intent.CATEGORY_LAUNCHER)
            startActivity(context!!, this, null)
        }
    }
}