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
    fun appDatabse_createDbAndRetriveCount_succes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        var count = dao?.getCount()
        Log.d(TAG, "createAndRetriveNotes: count $count")

        Assert.assertEquals(SampleAlarmData().getSampleAlarms().size, count)
    }

    @Test
    fun appDatabse_retriveDbValues_succes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        val original: AlarmEntity = SampleAlarmData().getSampleAlarms()[0]
        val fromDb: AlarmEntity = dao?.getAlarmById(1)!!

        Assert.assertEquals(original.name, fromDb.name)
        Assert.assertEquals(original.alarmMinutes, fromDb.alarmMinutes)
        Assert.assertEquals(original.isSnoozeEnabled, fromDb.isSnoozeEnabled)
        Assert.assertEquals(1, fromDb.id)
    }

    @Test
    fun appDatabse_removeSingleAlarmById_succes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        var count = 3
        dao?.deleteAlarmById(1)

        Assert.assertEquals(dao?.getCount(), count)
    }

    @Test
    fun appDatabse_selectAlarmById_succes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        var alarmName = "Jakiś Alarm"
        val alarm = dao?.getAlarmById(2)


        Assert.assertEquals(alarmName, alarm?.name)
    }

    @Test
    fun appDatabse_removeAllAlarms_succes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        dao?.deleteAll()

        Assert.assertEquals(0, dao?.getCount())
    }

    @Test
    fun appDatabse_updateIsAlarmEnabled_succes() {
        dao?.insertAll(SampleAlarmData().getSampleAlarms())
        dao?.updateIsAlarmOnById(false, 2)
        val alarmDisabled = dao?.getAlarmById(2)?.isAlarmEnabled
        Log.d(TAG, "appDatabse_updateIsAlarmEnabled_succes: $alarmDisabled")

        Assert.assertEquals(false, alarmDisabled)
    }


}