package com.marcinmejner.simplealarm.stoper

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.NumberPicker
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.adapters.AlarmsRecyclerViewAdapter
import com.marcinmejner.simplealarm.alarm.AlarmRingtoneChooserDialogFragment
import com.marcinmejner.simplealarm.alarm.AlarmSnoozeTimePickerDialogFragment
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.viewModels.AlarmEditorViewModel
import com.marcinmejner.simplealarm.viewModels.StoperEditorViewModel
import kotlinx.android.synthetic.main.activity_alarm_editor.*

class StoperEditor : AppCompatActivity() {
    private val TAG = "StoperEditor"

    //widgets
    lateinit var hourPicker: NumberPicker
    lateinit var minutePicker: NumberPicker
    lateinit var secPicker: NumberPicker

    //vars
    lateinit var stoperEditorViewModel: StoperEditorViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_editor)

        init()
    }

    private fun init() {
        initViewModel()
    }

    private fun initViewModel() {
        stoperEditorViewModel = ViewModelProviders.of(this)
                .get(StoperEditorViewModel::class.java)

    }


}
