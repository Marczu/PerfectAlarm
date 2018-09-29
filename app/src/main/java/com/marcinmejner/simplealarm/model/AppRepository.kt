package com.marcinmejner.simplealarm.model

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import com.marcinmejner.simplealarm.R.string.alarmId
import com.marcinmejner.simplealarm.utils.SampleAlarmData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppRepository private constructor(context: Context) {
    private val TAG = "AppRepository"

    var alarms: LiveData<List<AlarmEntity>>
    var stopers: LiveData<List<StoperEntity>>

    var db: AppDatabase
    val executor: Executor = Executors.newSingleThreadExecutor()

    init {
        Log.d(TAG, "AppRepository started: ")
        db = AppDatabase.getInstance(context)
        alarms = getAllAlarms()
        stopers = getAllStopers()
    }

    companion object {
        private var ourInstance: AppRepository? = null

        fun getInstance(context: Context): AppRepository {
            if (ourInstance == null) {
                ourInstance = AppRepository(context)
            }
            return ourInstance!!
        }
    }

    fun addSampleData() {
        executor.execute {
            db.alarmeDao().insertAll(SampleAlarmData().getSampleAlarms())
        }
    }

    fun deleteSingleAlarmById(alarmId: Int) {
        executor.execute {
            db.alarmeDao().deleteAlarmById(alarmId)
        }
    }

    private fun getAllAlarms(): LiveData<List<AlarmEntity>> {
        return db.alarmeDao().getall()
    }



    fun getAlarmById(alarmId: Int): LiveData<AlarmEntity> {
        return db.alarmeDao().getAlarmById(alarmId)
    }


    fun deleteAllAlarms() {
        executor.execute {
            db.alarmeDao().deleteAll()
        }
    }

    fun addNewAlarm(newAlarm: AlarmEntity) {
        executor.execute {
            db.alarmeDao().insertAlarm(newAlarm)
        }
    }

    fun updateIsAlarmOnById(isAlarmOn: Boolean, id: Int) {
        executor.execute {
            db.alarmeDao().updateIsAlarmOnById(isAlarmOn, id)
        }
    }

    fun updateAlarmById(alarmMinutes: String, alarmHours: String, snoozeMinutes: Int, name: String,
                        ringTone: String, daysOfWeek: String, isAlarmOn: Boolean, isSnoozeEnabled: Boolean, mondayCheck: Boolean, tuesdayCheck: Boolean, wednesdayCheck: Boolean,
                        thursdayCheck: Boolean, fridayCheck: Boolean, saturdayCheck: Boolean, sundayCheck: Boolean, id: Int) {
        executor.execute {
            db.alarmeDao().updateAlarmById(alarmMinutes, alarmHours, snoozeMinutes, name, ringTone,
                    daysOfWeek, isAlarmOn, isSnoozeEnabled, mondayCheck, tuesdayCheck, wednesdayCheck, thursdayCheck, fridayCheck,saturdayCheck, sundayCheck, id)
        }
    }

    /*Stoper Repo*/

    fun addNewStoper(newStoper: StoperEntity) {
        executor.execute {
            db.alarmeDao().insertStoper(newStoper)
        }
    }


    private fun getAllStopers(): LiveData<List<StoperEntity>> {
        return db.alarmeDao().getallStopers()
    }
    fun updateStoperStateById(running: Boolean, stopped: Boolean, paused: Boolean, id: Int){
        executor.execute {
            db.alarmeDao().updateStoperStateById(running, stopped, paused, id)
        }
    }

    fun updateStoperSecondsRemainingById(timeSecondsRemaining: Long, id: Int){
        executor.execute {
            db.alarmeDao().updateStoperSecondsRemainingById(timeSecondsRemaining, id)
        }
    }

    fun updateStoperCountdownById(stoperCountDown: Long, id: Int){
        executor.execute {
            db.alarmeDao().updateStoperCountdownById(stoperCountDown, id)
        }
    }

    fun deleteStoperById(id: Int) {
        executor.execute {
            db.alarmeDao().deleteStoperById(id)
        }
    }
}