package com.marcinmejner.simplealarm

import android.arch.persistence.room.Room
import android.support.test.InstrumentationRegistry
import android.support.test.runner.AndroidJUnit4
import android.util.Log
import com.marcinmejner.simplealarm.model.AlarmDao
import com.marcinmejner.simplealarm.model.AlarmEntity
import com.marcinmejner.simplealarm.model.AppDatabase
import com.marcinmejner.simplealarm.utils.SampleAlarmData
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith



@RunWith(AndroidJUnit4::class)
class DataBaseTest {
    private val TAG = "DataBaseTest"

    lateinit var db: AppDatabase
    var dao: AlarmDao? = null

    @Before
    fun createDb() {
        val context = InstrumentationRegistry.getTargetContext()
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase::class.java).build()
        dao = db.alarmeDao()
        Log.d(TAG, "createDb: created database")
    }

    @After
    fun closeDb() {
        db.close()
        Log.d(TAG, "closeDb: databse closed")
    }

    @Test
    fun createAndRetriveNotes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        var count = dao?.getCount()
        Log.d(TAG, "createAndRetriveNotes: count $count")

        Assert.assertEquals(SampleAlarmData().getSampleAlarms().size, count)
    }

    @Test
    fun compareStrings() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        val original: AlarmEntity = SampleAlarmData().getSampleAlarms()[0]
        val fromDb: AlarmEntity = dao?.getAlarmById(1)!!

        Assert.assertEquals(original.name, fromDb.name)
        Assert.assertEquals(original.alarmMinutes, fromDb.alarmMinutes)
        Assert.assertEquals(original.isSnoozeEnabled, fromDb.isSnoozeEnabled)
        Assert.assertEquals(1, fromDb.id)
    }

}