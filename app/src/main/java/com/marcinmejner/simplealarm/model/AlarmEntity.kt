package com.marcinmejner.simplealarm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "alarm")
data class AlarmEntity(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var alarmMinutes: String? = null,
        var alarmHours: String? = null,
        var snoozeMinutes: Int = 10,
        var name: String = "Mój budzik",
        var ringTone: String = "alarm_1",
        var daysOfWeek: String = "",
        var isAlarmEnabled: Boolean = true,
        var isSnoozeEnabled: Boolean = true,

        var mondayCheck: Boolean = true,
        var tuesdayCheck: Boolean = true,
        var wednesdayCheck: Boolean = true,
        var thursdayCheck: Boolean = true,
        var fridayCheck: Boolean = true,
        var saturdayCheck: Boolean = false,
        var sundayCheck: Boolean = false
)
