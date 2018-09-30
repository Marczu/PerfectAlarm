package com.marcinmejner.simplealarm.model

import android.arch.persistence.db.SupportSQLiteDatabase
import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.arch.persistence.room.TypeConverters
import android.content.Context
import com.marcinmejner.simplealarm.utils.SampleAlarmData
import kotlinx.coroutines.experimental.launch

@Database(entities = [(AlarmEntity::class), StoperEntity::class], version = 1)
@TypeConverters(DateConverter::class)
abstract class AppDatabase : RoomDatabase() {
    private val TAG = "AppDatabase"

    abstract fun alarmeDao(): AlarmDao

    companion object {
        val DATABASE_NAME = "alarmDatabase.db"
        var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            if (INSTANCE == null) {
                synchronized(AppDatabase::class) {
                    INSTANCE = Room.databaseBuilder(context.applicationContext,
                            AppDatabase::class.java, DATABASE_NAME)
                            .addCallback(object : RoomDatabase.Callback() {
                                override fun onCreate(db: SupportSQLiteDatabase) {
                                    super.onCreate(db)
                                    launch {
                                        getInstance(context).alarmeDao().insertAlarm(SampleAlarmData().insertFirstAlarm())
                                    }
                                }
                            })
                            .build()
                }
            }
            return INSTANCE!!
        }

        fun destroyInstance() {
            INSTANCE = null
        }
    }
}




