package com.marcinmejner.simplealarm.utils

import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.StoperEntity

class SampleStoperData {

    fun getSampleStoper() {
        val stopers = mutableListOf<StoperEntity>()

    }

    fun insertFirstStoper() {

    }

    fun insertStubStoper(): List<StoperEntity> {
        val stopers = mutableListOf<StoperEntity>()
        stopers.add(StoperEntity(alarmCountDown = "00:05:44"))
        stopers.add(StoperEntity(alarmCountDown = "00:12:09"))
        return stopers
    }
}