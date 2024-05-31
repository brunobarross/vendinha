package com.altamirobruno.vendinha.model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.altamirobruno.vendinha.Customer

@Database(entities = [Customer::class], version = 3)
@TypeConverters(DateConverter::class)

abstract class AppDatabase : RoomDatabase() {

    abstract fun customerDao(): CustomerDao

    companion object {
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context) : AppDatabase {
            return if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        AppDatabase::class.java,
                        "vendinha"
                    ).build()
                }
                INSTANCE as AppDatabase
            } else {
                INSTANCE as AppDatabase
            }
        }
    }

}