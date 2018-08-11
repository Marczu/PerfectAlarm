package com.marcinmejner.simplealarm.utils

import com.marcinmejner.simplealarm.model.AlarmEntity

class SampleAlarmData {

    fun getSampleAlarms(): List<AlarmEntity> {
        val alarms = mutableListOf<AlarmEntity>()
        alarms.add(AlarmEntity(alarmMinutes = "20", alarmHours = "14", name = "Testowy Alarm", isSnoozeEnabled = true, snoozeMinutes = 50, isAlarmEnabled = false ))
        alarms.add(AlarmEntity(alarmMinutes = "50", alarmHours = "1", name = "Jakiś Alarm", isSnoozeEnabled = true, snoozeMinutes = 7 ))
        alarms.add(AlarmEntity(alarmMinutes = "22", alarmHours = "3", name = "Kolejny Testowy Alarm", isSnoozeEnabled = false, snoozeMinutes = 17 ))
        alarms.add(AlarmEntity(alarmMinutes = "50", alarmHours = "11", name = "Mój", isSnoozeEnabled = true, snoozeMinutes = 7, isAlarmEnabled = false ))

        return alarms
    }

}