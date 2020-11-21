package com.demoproject.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import demo.navigations.model.CouponDataDB
import demo.navigations.model.StudentDB
import javax.inject.Inject

@Database(entities = [StudentDB::class,CouponDataDB::class], version = 3, exportSchema = false)
abstract class MyRoomDataBase() : RoomDatabase() {

    public abstract fun getNoteDao(): WeatherDao

    companion object {
        @Volatile
        private var INSTANCE: MyRoomDataBase? = null
        fun getInstance(context: Context): MyRoomDataBase {
            synchronized(this) {
                var instance = INSTANCE
                if (instance == null) {
                    instance = Room.databaseBuilder(context,MyRoomDataBase::class.java,"student")
                        .allowMainThreadQueries()
                        .build()
                    INSTANCE = instance
                }
                return instance
            }
        }
    }
}