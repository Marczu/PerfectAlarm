package com.marcinmejner.simplealarm.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "stoper")
data class StoperEntity(
        @PrimaryKey(autoGenerate = true) var id: Int = 0,
        var alarmCountDown: String? = null,
        var pauseIsOn: Boolean = false,
        var stopIsOn: Boolean = false,
        var playIsOn: Boolean = false
)
