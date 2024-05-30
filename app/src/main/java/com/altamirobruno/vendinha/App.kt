package com.altamirobruno.vendinha

import android.app.Application
import com.altamirobruno.vendinha.model.AppDatabase

class App : Application() {
    lateinit var db: AppDatabase

    override fun onCreate() {
        super.onCreate()
        db = AppDatabase.getDatabase(this)
    }
}