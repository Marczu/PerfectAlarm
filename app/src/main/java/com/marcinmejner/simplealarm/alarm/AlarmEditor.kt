package com.marcinmejner.simplealarm.alarm

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModelProviders
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.widget.NumberPicker
import com.marcinmejner.simplealarm.R
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.viewModels.AlarmEditorViewModel
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
        setDataFromEditingAlarm()
        cancel()
    }

    private fun initViewModel() {
        editorViewModel = ViewModelProviders.of(this)
                .get(AlarmEditorViewModel::class.java)

        editorViewModel.snoozeTime.observe(this, android.arch.lifecycle.Observer {
            Log.d(TAG, "initViewModel: z obserwera : ${it!!}")
            edit_snooze_minutes_et.text = "$it minut"
        })

        editorViewModel.ringtone.observe(this, android.arch.lifecycle.Observer {
            edit_ringtone_set_tv.text = "Wybrano: $it"
        })
    }

    /*Taking data from widgets and saveing it in databse*/
    private fun saveNewAlarm() {
        edit_save_btn.setOnClickListener {

            val hourPicked = hourPicker.value.toString()
            val minutesPicked = minutePicker.value.toString()
            val snoozeMinutes = editorViewModel.snoozeTime.value
            val ringtone = editorViewModel.ringtone.value
            val daysOfWeekText = editorViewModel.setDaysOfWeekText()
            val isAlarmEnabled = editorViewModel.isAlarmEnabled

            val monday = editorViewModel.mondayToggle
            val tuesday = editorViewModel.tuesdayToggle
            val wednesday = editorViewModel.wednesdayToggle
            val thursday = editorViewModel.thursdayToggle
            val friday = editorViewModel.fridayToggle
            val saturday = editorViewModel.saturdayToggle
            val sunday = editorViewModel.sundayToggle
            Log.d(TAG, "saveNewAlarm: $hourPicked : $minutesPicked")

            val alarmName = editorViewModel.isTitleEmpty(edit_alarm_title_et.text.toString())
            Log.d(TAG, "saveNewAlarm: $alarmName")

            val newAlarm = AlarmEntity(alarmMinutes = minutesPicked, alarmHours = hourPicked,
                    name = alarmName, snoozeMinutes = snoozeMinutes!!, ringTone = ringtone!!,
                    daysOfWeek = daysOfWeekText, isAlarmEnabled = isAlarmEnabled, mondayCheck = monday,
                    tuesdayCheck = tuesday, wednesdayCheck = wednesday, thursdayCheck = thursday,
                    fridayCheck = friday, saturdayCheck = saturday, sundayCheck = sunday)

            val extra = intent.extras

            /*Update Existing alarm when comming from edit button*/
            if (extra != null) {
                val id = extra.getInt(getString(R.string.alarmId))
                editorViewModel.updateAlarmById(newAlarm.alarmMinutes!!, newAlarm.alarmHours!!, newAlarm.snoozeMinutes, newAlarm.name,
                        newAlarm.ringTone, newAlarm.daysOfWeek, newAlarm.isAlarmEnabled, newAlarm.isSnoozeEnabled,
                        newAlarm.mondayCheck, newAlarm.tuesdayCheck, newAlarm.wednesdayCheck,
                        newAlarm.thursdayCheck, newAlarm.fridayCheck, newAlarm.saturdayCheck, newAlarm.sundayCheck, id)

            /*Create new Alarm*/
            } else {
                editorViewModel.addNewAlarm(newAlarm)
            }
            finish()
        }
    }

    private fun initTimePicker() {

        /*Setting hour values 00 to 23*/
        val displaydHourValues = arrayOfNulls<String>(24)
        for (i in 0..23) {
            if (i < 10) {
                displaydHourValues[i] = "0$i"
            } else {
                displaydHourValues[i] = "$i"
            }
        }

        /*Setting minutes values 00 to 59*/
        val displayMinutesdValues = arrayOfNulls<String>(60)
        for (i in 0..59) {
            if (i < 10) {
                displayMinutesdValues[i] = "0$i"
            } else {
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
        minutePicker.maxValue = 59
    }

    /*show snoozeTimePicker fragment*/
    private fun setSnoozeTimePicker() {
        edit_snooze_minutes_relLayout.setOnClickListener {
            val snnozeDialog = AlarmSnoozeTimePickerDialogFragment()
            val fm = supportFragmentManager

            snnozeDialog.show(fm, getString(R.string.select_snooze_time))
        }
    }

    /*show ringToneChooser fragment fragment*/
    private fun setRingtoneChooser() {
        edit_ringtone_relLayout.setOnClickListener {
            val ringtoneDialog = AlarmRingtoneChooserDialogFragment()
            val fm = supportFragmentManager

            ringtoneDialog.show(fm, getString(R.string.select_ringhtone))
        }
    }

    private fun daysOfWeekChooser() {

        poniedzialek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.mondayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser: monday ${editorViewModel.mondayToggle}")
        }

        wtorek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.tuesdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser: tuesday ${editorViewModel.tuesdayToggle}")
        }

        sroda_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.wednesdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  wednesday ${editorViewModel.wednesdayToggle}")
        }

        czwartek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.thursdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  thursday ${editorViewModel.thursdayToggle}")
        }

        piatek_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.fridayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser: friday ${editorViewModel.fridayToggle}")
        }

        sobota_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.saturdayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  saturday ${editorViewModel.saturdayToggle}")
        }

        niedziela_toggle.setOnCheckedChangeListener { buttonView, isChecked ->
            editorViewModel.sundayToggle = isChecked
            Log.d(TAG, "daysOfWeekChooser:  sunday ${editorViewModel.sundayToggle}")
        }

    }

    /*Get data from existing alarm from AlarmFragment, and set it to widgets*/
    private fun setDataFromEditingAlarm() {
        val extra = intent.extras
        if (extra != null) {
            val id = extra.getInt(getString(R.string.alarmId))

            /*get data from Room and look for alarm ID that come from intent*/
            editorViewModel.alarms.observe(this@AlarmEditor, Observer<List<AlarmEntity>>{
                it?.forEach {
                    if (it.id == id) {
                        editorViewModel.existingAlarm.value = it
                    }
                }
            })

            /*Update widgets values*/
            editorViewModel.existingAlarm.observe(this@AlarmEditor, Observer<AlarmEntity> {
                hourPicker.value = it?.alarmHours?.toInt()!!
                minutePicker.value = it.alarmMinutes?.toInt()!!

                edit_alarm_title_et.setText(it.name)
                editorViewModel.snoozeTime.value = it.snoozeMinutes
                editorViewModel.isAlarmEnabled = it.isAlarmEnabled
                editorViewModel.ringtone.value = it.ringTone

                editorViewModel.mondayToggle = it.mondayCheck
                poniedzialek_toggle.isChecked = it.mondayCheck

                editorViewModel.tuesdayToggle = it.tuesdayCheck
                wtorek_toggle.isChecked = it.tuesdayCheck

                editorViewModel.wednesdayToggle = it.wednesdayCheck
                sroda_toggle.isChecked = it.wednesdayCheck

                editorViewModel.thursdayToggle = it.thursdayCheck
                czwartek_toggle.isChecked = it.thursdayCheck

                editorViewModel.fridayToggle = it.fridayCheck
                piatek_toggle.isChecked = it.fridayCheck

                editorViewModel.saturdayToggle = it.saturdayCheck
                sobota_toggle.isChecked = it.saturdayCheck

                editorViewModel.sundayToggle = it.sundayCheck
                niedziela_toggle.isChecked = it.sundayCheck
            })
        }
    }

    private fun cancel() {
        edit_cancel_btn.setOnClickListener {
            finish()
        }
    }
}
