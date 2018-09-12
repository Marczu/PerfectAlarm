package com.marcinmejner.simplealarm.broadcast

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.support.v4.content.ContextCompat.startActivity
import android.util.Log
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.alarm.AlarmRingingScreen
import com.marcinmejner.simplealarm.alarm.AlarmStarterSetup

class MyBroadcastReciver : BroadcastReceiver() {
    private val TAG = "MyBroadcastReciver"

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(TAG, "onReceive: inside onRecive")

        if (intent?.action.equals("com.marcinmejner.alarmmanager")) {
            var extras = intent?.extras
            val id = extras?.getInt(context?.getString(R.string.id))
            Log.d(TAG, "onReceive: alarm started, id: $id")
            initAlarmActivity(context, id)
        } else if (intent?.action.equals("android.permission.RECEIVE_BOOT_COMPLETED")) {
            val alarmStarterSetup = AlarmStarterSetup()
            alarmStarterSetup.alarmsStarter(context!!)
        }
    }

    /*Go to AlarmRingingScreen*/
    private fun initAlarmActivity(context: Context?, id: Int?) {
            Intent(context, AlarmRingingScreen::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT or Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_PREVIOUS_IS_TOP
                action = "android.intent.action.VIEW"
                addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
                action = Intent.ACTION_MAIN
                addCategory(Intent.CATEGORY_LAUNCHER)
                putExtra(context?.getString(R.string.alarm_ring_id), id)
                startActivity(context!!, this, null)
            }
    }
}