package com.marcinmejner.simplealarm.alarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.util.Log
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppRepository

class AlarmsViewModel(application: Application) : AndroidViewModel(application) {
    private val TAG = "AlarmsViewModel"

    var repository: AppRepository = AppRepository.getInstance(application.applicationContext)
    var alarms: LiveData<List<AlarmEntity>>

    init {
        alarms = repository.alarms
    }

    fun addSampleData() {
        repository.addSampleData()
    }

    fun addNewAlarm(newAlarm: AlarmEntity){
        repository.addNewAlarm(newAlarm)
    }

    fun deleteAllAlarms(){
        repository.deleteAllAlarms()
    }

    fun deleteSingleAlarmById(id: Int){
        repository.deleteSingleAlarmById(id)
    }

}