package com.marcinmejner.simplealarm

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.Observer
import android.arch.persistence.room.Room
import android.content.Context
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
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.mockito.junit.MockitoJUnitRunner
import android.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.rules.TestRule
import org.junit.Rule
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@RunWith(AndroidJUnit4::class)
class DataBaseTest {
    private val TAG = "DataBaseTest"

    lateinit var context: Context
    lateinit var db: AppDatabase
    var dao: AlarmDao? = null



    @Before
    fun createDb() {
        context = InstrumentationRegistry.getTargetContext()
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
        val fromDb = dao?.getAlarmById(1)?.blockingObserve()

        Log.d(TAG, "appDatabse_retriveDbValues_succes: ${fromDb?.alarmHours}")
        Assert.assertEquals(original.name, fromDb?.name)
        Assert.assertEquals(original.alarmMinutes, fromDb?.alarmMinutes)
        Assert.assertEquals(original.isSnoozeEnabled, fromDb?.isSnoozeEnabled)
        Assert.assertEquals(1, fromDb?.id)
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
        val alarm = dao?.getAlarmById(2)?.blockingObserve()


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
        val alarmDisabled = dao?.getAlarmById(2)?.blockingObserve()
        Log.d(TAG, "appDatabse_updateIsAlarmEnabled_succes: $alarmDisabled")

//        val count = CountDownLatch(1)
//        count.await(5000, TimeUnit.MILLISECONDS)


        Assert.assertEquals(false, alarmDisabled?.isAlarmEnabled)
    }

    private fun <T> LiveData<T>.blockingObserve(): T? {
        var value: T? = null
        val latch = CountDownLatch(1)

        val observer = Observer<T> { t ->
            value = t
            latch.countDown()
        }

        observeForever(observer)

        latch.await(2, TimeUnit.SECONDS)
        return value
    }


}