package com.marcinmejner.simplealarm.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppRepository

class AlarmRingingViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = "AlarmRingingViewModel"

    var repository: AppRepository = AppRepository.getInstance(application.applicationContext)
    var alarms: LiveData<List<AlarmEntity>>
    var currentAlarm: MutableLiveData<AlarmEntity> = MutableLiveData()

    init {
        alarms = repository.alarms
    }


}