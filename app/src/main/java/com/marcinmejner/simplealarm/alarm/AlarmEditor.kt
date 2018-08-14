package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.NumberPicker
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
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
        setSnoozeTimePicker()
        setRingtoneChooser()
        cancel()
    }

    private fun initViewModel() {
        editorViewModel = ViewModelProviders.of(this)
                .get(AlarmEditorViewModel::class.java)
    }

    /*Taking data from widgets and saveing it in databse*/
    private fun saveNewAlarm() {
        edit_save_btn.setOnClickListener {

            /*pick hour and minutes*/
            val hourPicked = hourPicker.value.toString()
            val minutesPicked = minutePicker.value.toString()
            Log.d(TAG, "saveNewAlarm: $hourPicked : $minutesPicked")

            val alarmName = edit_alarm_title_et.text.toString()
            Log.d(TAG, "saveNewAlarm: $alarmName")



            val newAlarm = AlarmEntity(alarmMinutes = minutesPicked, alarmHours = hourPicked, name = alarmName)

            editorViewModel.addNewAlarm(newAlarm)

            finish()
        }

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
        hourPicker.value = 7

        minutePicker = numberMinutePicker
        minutePicker.displayedValues = displayMinutesdValues
        minutePicker.minValue = 0
        minutePicker.maxValue = 60
    }

    fun setSnoozeTimePicker(){
        edit_snooze_minutes_relLayout.setOnClickListener {
            val snnozeDialog = AlarmSnoozeTimePickerDialogFragment()
            val fm = supportFragmentManager

            snnozeDialog.show(fm, getString(R.string.select_snooze_time))
        }
    }

    fun setRingtoneChooser(){
        edit_ringtone_relLayout.setOnClickListener {
            val ringtoneDialog = AlarmRingtoneChooserDialogFragment()
            val fm = supportFragmentManager

            ringtoneDialog.show(fm, getString(R.string.select_ringhtone))
        }
    }

    private fun cancel(){
        edit_cancel_btn.setOnClickListener {
            finish()
        }
    }

}
