package com.marcinmejner.simplealarm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*
import kotlin.collections.ArrayList


@Entity(tableName = "alarm")
data class AlarmEntity(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
//        var alarmTime: Date? = null,
        var alarmMinutes: String? = null,
        var alarmHours: String? = null,
        var snoozeMinutes: Int = 10,
//        var snoozeTime: Int? = null,
        var name: String = "Mój budzik",
        var ringTone: String = "alarm_1",
        var daysOfWeek: String = "",
//        var daysOfWeekCheck: ArrayList<Boolean>? = null,
        var isAlarmEnabled: Boolean = true,
        var isSnoozeEnabled: Boolean = false
)
