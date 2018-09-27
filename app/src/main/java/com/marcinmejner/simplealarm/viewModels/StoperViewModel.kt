package com.marcinmejner.simplealarm.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppRepository
import com.marcinmejner.simplealarm.model.StoperEntity

class StoperViewModel(application: Application) : AndroidViewModel(application){
    private val TAG = "StoperViewModel"

    var repository: AppRepository = AppRepository.getInstance(application.applicationContext)
    var stopers: LiveData<List<StoperEntity>>

    init {
        stopers = repository.stopers
    }

}