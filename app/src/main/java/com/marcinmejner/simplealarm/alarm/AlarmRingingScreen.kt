package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.media.MediaPlayer
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Html
import android.util.Log
import android.view.WindowManager
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.viewModels.AlarmRingingViewModel
import kotlinx.android.synthetic.main.activity_alarm_ringing_screen.*

class AlarmRingingScreen : AppCompatActivity() {
    private val TAG = "AlarmRingingScreen"

    lateinit var alarmRingingViewModel: AlarmRingingViewModel
    lateinit var alarm: AlarmEntity
    lateinit var player: MediaPlayer

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        init()
    }

    private fun init() {
        initSetupScreen()
        initAlarmViewModel()
        getCurrentTimeAndDay()
        getAlarm()
        stopAlarm()
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

    private fun initAlarmViewModel() {
        alarmRingingViewModel = ViewModelProviders.of(this)
                .get(AlarmRingingViewModel::class.java)
    }

    private fun getAlarm() {
        val extra = intent.extras
        Log.d(TAG, "getAlarm: ${extra.getInt(getString(R.string.alarm_ring_id))}")
        if (extra != null) {
            val alarmId = extra.getInt(getString(R.string.alarm_ring_id))

            /*get data from Room and look for alarm ID that come from intent*/
            alarmRingingViewModel.alarms.observe(this, Observer<List<AlarmEntity>> {
                it?.forEach {
                    if (it.id == alarmId) {
                        alarmRingingViewModel.currentAlarm.value = it
                        Log.d(TAG, "getAlarm: ${it.ringTone}")
                        startAlarm()
                    }
                }
            })
        }
    }

    private fun startAlarm(){
        val choosenRingtone = alarmRingingViewModel.currentAlarm.value?.ringTone
                ?.toLowerCase()?.replace(" ", "_")
        Log.d(TAG, "startAlarm: $choosenRingtone")
        val resID = resources.getIdentifier(choosenRingtone,
                "raw", this.packageName)

        player = MediaPlayer.create(this, resID)
        player.isLooping = true
        player.start()
    }

    private fun stopAlarm(){
        btn_stop_alarm.setOnClickListener {
            player.stop()
            finish()
        }
    }

    private fun getCurrentTimeAndDay(){
        alarm_ringing_hour.text = alarmRingingViewModel.getCurrentHour()
    }
}
