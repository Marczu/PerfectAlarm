package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.marcinmejner.simplealarm.R

class AlarmEditor : AppCompatActivity() {
    private val TAG = "AlarmEditor"

    //vars
    lateinit var editorViewModel: AlarmEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_editor)

        init()
    }

    private fun init() {
        initViewModel()
        saveNewAlarm()
    }

    private fun initViewModel() {
        editorViewModel = ViewModelProviders.of(this)
                .get(AlarmEditorViewModel::class.java)
    }

    private fun saveNewAlarm() {


    }

}
