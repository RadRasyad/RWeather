package com.rad.rweather.core.data.source.local.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.rad.rweather.core.data.source.local.entity.*
import com.rad.rweather.core.utils.DataConverter

@Database(
    entities = [ForecastEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(DataConverter::class)
abstract class ForecastDatabase: RoomDatabase() {

    abstract fun forecastDao(): ForecastDao

    companion object{
        @Volatile
        private var INSTANCE: ForecastDatabase? = null

        fun getInstance(context: Context): ForecastDatabase =
            INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    ForecastDatabase::class.java,
                    "Forecast.db"
                ).fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
    }
}