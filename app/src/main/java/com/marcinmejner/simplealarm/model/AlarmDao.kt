package com.marcinmejner.simplealarm.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarm(alarmEntity: AlarmEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(notes: List<AlarmEntity>)

    @Delete
    fun deleteAlarm(alarmEntity: AlarmEntity)

    @Query("SELECT * FROM alarm WHERE id = :id")
    fun getAlarmById(id: Int): LiveData<AlarmEntity>

    @Query("DELETE FROM alarm WHERE id = :id ")
    fun deleteAlarmById(id: Int) : Int

    @Query("SELECT COUNT(*) FROM alarm")
    fun getCount(): Int

    @Query("SELECT * FROM alarm")
    fun getall() : LiveData<List<AlarmEntity>>

    @Query("DELETE FROM alarm ")
    fun deleteAll() : Int

    @Query("UPDATE alarm SET isAlarmEnabled = :isAlarmOn WHERE id = :id")
    fun updateIsAlarmOnById(isAlarmOn: Boolean, id: Int)

    @Query("UPDATE alarm SET alarmMinutes = :alarmMinutes, alarmHours = :alarmHours, snoozeMinutes = :snoozeMinutes, name = :name, ringTone = :ringTone, daysOfWeek = :daysOfWeek, isAlarmEnabled = :isAlarmOn, isSnoozeEnabled = :isSnoozeEnabled, mondayCheck = :mondayCheck, tuesdayCheck = :tuesdayCheck, wednesdayCheck = :wednesdayCheck, thursdayCheck = :thursdayCheck, fridayCheck = :fridayCheck, saturdayCheck = :saturdayCheck, sundayCheck = :sundayCheck  WHERE id = :id")
    fun updateAlarmById(alarmMinutes: String, alarmHours: String, snoozeMinutes: Int, name: String,
                        ringTone: String, daysOfWeek: String, isAlarmOn: Boolean,
                        isSnoozeEnabled: Boolean, mondayCheck: Boolean, tuesdayCheck: Boolean, wednesdayCheck: Boolean,
                        thursdayCheck: Boolean, fridayCheck: Boolean, saturdayCheck: Boolean, sundayCheck: Boolean, id: Int)
}