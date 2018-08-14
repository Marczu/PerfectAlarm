package com.marcinmejner.simplealarm.alarm

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppRepository

class AlarmEditorViewModel(application: Application) : AndroidViewModel(application) {

    var repository: AppRepository = AppRepository.getInstance(application.applicationContext)
    var alarms: LiveData<List<AlarmEntity>>
    var snoozeTime: MutableLiveData<Int> = MutableLiveData()

    init {
        alarms = repository.alarms
    }

    fun addNewAlarm(newAlarm: AlarmEntity){
        repository.addNewAlarm(newAlarm)
    }
}