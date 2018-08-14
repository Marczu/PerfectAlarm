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
    var ringtone: MutableLiveData<String> = MutableLiveData()

    init {
        snoozeTime.value = 10
        ringtone.value = "budzik"
        alarms = repository.alarms
    }

    fun isTitleEmpty(title: String): String{
        if(title.isBlank()){
            return "Mój alarm"
        }
        else return title
    }

    fun addNewAlarm(newAlarm: AlarmEntity){
        repository.addNewAlarm(newAlarm)
    }
}