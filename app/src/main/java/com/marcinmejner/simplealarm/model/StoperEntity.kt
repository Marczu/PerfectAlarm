package com.marcinmejner.simplealarm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stoper")
data class StoperEntity(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var alarmCountDown: Long = 60L,
        var timeSecondsRemaining: Long = 60L,
        var running: Boolean = false,
        var stopped: Boolean = true,
        var paused: Boolean = true
)
