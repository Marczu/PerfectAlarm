package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.NumberPicker
import com.marcinmejner.simplealarm.R
import kotlinx.android.synthetic.main.activity_alarm_editor.*
import java.util.*

class AlarmEditor : AppCompatActivity() {
    private val TAG = "AlarmEditor"

    //widgets
    lateinit var hourPicker: NumberPicker
    lateinit var minutePicker: NumberPicker

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
        initTimePicker()
    }

    private fun initTimePicker() {

        /*Setting hour values 00 to 23*/
        val displaydHourValues = arrayOfNulls<String>(24)
        for (i in 0..23) {
            if (i<10){
                displaydHourValues[i] = "0$i"
            }else{
                displaydHourValues[i] = "$i"
            }
        }

        /*Setting minutes values 00 to 60*/
        val displayMinutesdValues = arrayOfNulls<String>(61)
        for (i in 0..60) {
            if (i<10){
                displayMinutesdValues[i] = "0$i"
            }else{
                displayMinutesdValues[i] = "$i"
            }
        }

        hourPicker = numberHourPicker
        hourPicker.displayedValues = displaydHourValues
        hourPicker.minValue = 0
        hourPicker.maxValue = 23

        minutePicker = numberMinutePicker
        minutePicker.displayedValues = displayMinutesdValues
        minutePicker.minValue = 0
        minutePicker.maxValue = 60
    }

    private fun initViewModel() {
        editorViewModel = ViewModelProviders.of(this)
                .get(AlarmEditorViewModel::class.java)
    }

    private fun saveNewAlarm() {


    }

}
