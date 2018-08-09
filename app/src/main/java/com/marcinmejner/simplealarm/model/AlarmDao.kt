package com.marcinmejner.simplealarm.model

import android.arch.lifecycle.LiveData
import android.arch.persistence.room.*

@Dao
interface AlarmDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAlarm(alarmEntity: AlarmEntity)

    @Delete
    fun deleteAlarm(alarmEntity: AlarmEntity)

    @Query("SELECT * FROM alarm WHERE id = :id")
    fun getAlarmById(id: Int): AlarmEntity


    @Query("DELETE FROM alarm WHERE id = :id ")
    fun deleteAlarmById(id: Int) : Int

    @Query("SELECT COUNT(*) FROM alarm")
    fun getCount(): Int

}