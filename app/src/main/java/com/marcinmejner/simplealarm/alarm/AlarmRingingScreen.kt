package com.marcinmejner.simplealarm.alarm

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.WindowManager
import com.marcinmejner.simplealarm.R

class AlarmRingingScreen : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        initSetupScreen()
    }

    private fun initSetupScreen() {
        /*Activity pop in lock screen*/
        window.apply {
            addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED)
            addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD)
            addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
            addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON)
        }

        setContentView(R.layout.activity_alarm_ringing_screen)
    }
}
