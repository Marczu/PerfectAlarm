package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.NumberPicker
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
import kotlinx.android.synthetic.main.activity_alarm_editor.*

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
        daysOfWeekChooser()
        cancel()
    }

    private fun initViewModel() {
        editorViewModel = ViewModelProviders.of(this)
                .get(AlarmEditorViewModel::class.java)

        editorViewModel.snoozeTime.observe(this, android.arch.lifecycle.Observer {
            Log.d(TAG, "initViewModel: z obserwera : ${it!!}")
            edit_snooze_minutes_et.text = "$it minut"
        })
    }

    /*Taking data from widgets and saveing it in databse*/
    private fun saveNewAlarm() {
        edit_save_btn.setOnClickListener {

            val hourPicked = hourPicker.value.toString()
            val minutesPicked = minutePicker.value.toString()
            val snoozeMinutes = editorViewModel.snoozeTime.value
            val ringtone = editorViewModel.ringtone.value
            Log.d(TAG, "saveNewAlarm: $hourPicked : $minutesPicked")

            val alarmName = editorViewModel.isTitleEmpty(edit_alarm_title_et.text.toString())
            Log.d(TAG, "saveNewAlarm: $alarmName")

            /*Adding new alarm to databse*/
            val newAlarm = AlarmEntity(alarmMinutes = minutesPicked, alarmHours = hourPicked, name = alarmName, snoozeMinutes = snoozeMinutes!!, ringTone = ringtone!!)
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

    /*show snoozeTimePicker fragment*/
    private fun setSnoozeTimePicker(){
        edit_snooze_minutes_relLayout.setOnClickListener {
            val snnozeDialog = AlarmSnoozeTimePickerDialogFragment()
            val fm = supportFragmentManager

            snnozeDialog.show(fm, getString(R.string.select_snooze_time))
        }
    }

    /*show ringToneChooser fragment fragment*/
    private fun setRingtoneChooser(){
        edit_ringtone_relLayout.setOnClickListener {
            val ringtoneDialog = AlarmRingtoneChooserDialogFragment()
            val fm = supportFragmentManager

            ringtoneDialog.show(fm, getString(R.string.select_ringhtone))
        }
    }

    private fun daysOfWeekChooser() {
        var mondayToggle: Boolean
        var tuesdayToggle: Boolean
        var wednesdayToggle: Boolean
        var thursdayToggle: Boolean
        var fridayToggle: Boolean
        var saturdayToggle: Boolean
        var sundayToggle: Boolean
        poniedzialek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            mondayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser: monday $mondayToggle")
        }

        wtorek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            tuesdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser: tuesday $tuesdayToggle")
        }

        sroda_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            wednesdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  wednesday $wednesdayToggle")
        }

        czwartek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            thursdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  thursday $thursdayToggle")
        }

        piatek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            fridayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser: friday $fridayToggle")
        }

        sobota_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            saturdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  saturday $saturdayToggle")
        }

        niedziela_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            sundayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  sunday $sundayToggle")
        }
//        if(wtorek_toggle.isChecked) mondayToggle = true
//        if(poniedzialek_toggle.isChecked) mondayToggle = true
//        if(poniedzialek_toggle.isChecked) mondayToggle = true
//        if(poniedzialek_toggle.isChecked) mondayToggle = true
//        if(poniedzialek_toggle.isChecked) mondayToggle = true
//        if(poniedzialek_toggle.isChecked) mondayToggle = true
    }

    private fun cancel(){
        edit_cancel_btn.setOnClickListener {
            finish()
        }
    }
}
