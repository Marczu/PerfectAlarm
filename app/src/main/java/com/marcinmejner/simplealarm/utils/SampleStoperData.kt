package com.marcinmejner.simplealarm.utils

import com.marcinmejner.simplealarm.model.StoperEntity

class SampleStoperData {

    fun getSampleStoper() {
        val stopers = mutableListOf<StoperEntity>()

    }

    fun insertFirstStoper() {

    }

    fun insertStubStoper(): List<StoperEntity> {
        val stopers = mutableListOf<StoperEntity>()
        stopers.add(StoperEntity(stoperCountDown = 120))
        stopers.add(StoperEntity(stoperCountDown = 120))
        return stopers
    }
}