package com.marcinmejner.simplealarm.model

import android.arch.lifecycle.LiveData
import android.content.Context
import android.util.Log
import com.marcinmejner.simplealarm.utils.SampleAlarmData
import java.util.concurrent.Executor
import java.util.concurrent.Executors

class AppRepository private constructor(context: Context) {
    private val TAG = "AppRepository"

    var alarms: LiveData<List<AlarmEntity>>
    var db: AppDatabase
    val executor: Executor = Executors.newSingleThreadExecutor()

    init {
        Log.d(TAG, "AppRepository started: ")
        db = AppDatabase.getInstance(context)
        alarms = getAllAlarms()
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

    fun getAllAlarms(): LiveData<List<AlarmEntity>> {
        return db.alarmeDao().getall()
    }

    fun getAlarmById(alarmId: Int): AlarmEntity? {
        return db.alarmeDao().getAlarmById(alarmId)
    }

    fun insertNote(alarm: AlarmEntity) {
        executor.execute {
            db.alarmeDao().insertAlarm(alarm)
        }
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
}