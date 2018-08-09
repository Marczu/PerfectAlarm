package com.marcinmejner.simplealarm.utils

import com.marcinmejner.simplealarm.model.AlarmEntity

class SampleAlarmData {

    fun getNotes(): AlarmEntity {
        val alarm = AlarmEntity(alarmMinutes = "20", alarmHours = "14", name = "Testowy Alarm", isSnoozeEnabled = true, snoozeMinutes = 50 )

        return alarm
    }

}