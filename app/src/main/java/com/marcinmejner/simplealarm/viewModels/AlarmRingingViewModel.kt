package com.marcinmejner.simplealarm.viewModels

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import android.media.MediaPlayer
import android.util.Log
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppRepository
import java.text.SimpleDateFormat
import java.util.*

class AlarmRingingViewModel(application: Application): AndroidViewModel(application) {
    private val TAG = "AlarmRingingViewModel"

    lateinit var player: MediaPlayer
    var repository: AppRepository = AppRepository.getInstance(application.applicationContext)
    var alarms: LiveData<List<AlarmEntity>>
    var currentAlarm: MutableLiveData<AlarmEntity> = MutableLiveData()

    init {
        alarms = repository.alarms
    }

    fun getCurrentDay(): String {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek = sdf.format(d)

        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        val hoursAfter = if(hours < 10){
            "0$hours"
        }else{
            "$hours"
        }

        val minutesAfter = if(minutes < 10){
            "0$minutes"
        }else{
            "$minutes"
        }

        return "Mamy $dayOfTheWeek"
    }

    fun getCurrentHour(): String {
        val sdf = SimpleDateFormat("EEEE")
        val d = Date()
        val dayOfTheWeek = sdf.format(d)

        val calendar = Calendar.getInstance()
        val hours = calendar.get(Calendar.HOUR_OF_DAY)
        val minutes = calendar.get(Calendar.MINUTE)

        val hoursAfter = if(hours < 10){
            "0$hours"
        }else{
            "$hours"
        }

        val minutesAfter = if(minutes < 10){
            "0$minutes"
        }else{
            "$minutes"
        }

        return "$hoursAfter:$minutesAfter"
    }
}