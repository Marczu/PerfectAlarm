package com.marcinmejner.simplealarm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey
import java.util.*


@Entity(tableName = "alarm")
data class AlarmEntity(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var alarmTime: Date? = null,
        var snoozeMinutes: Int = -1,
        var snoozeTime: Int = 10,
        var name: String = "",
        var ringTone: String = "",
        var isAlarmEnabled: Boolean = true,
        var isSnoozeEnabled: Boolean = false
)
