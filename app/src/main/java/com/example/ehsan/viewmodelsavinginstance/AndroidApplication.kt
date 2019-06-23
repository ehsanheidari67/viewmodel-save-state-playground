package com.example.ehsan.viewmodelsavinginstance

import android.app.Application
import com.example.ehsan.viewmodelsavinginstance.data.repository.SimpleDataRepository
import timber.log.Timber

class AndroidApplication : Application() {

    val repository: SimpleDataRepository by lazy {
        SimpleDataRepository()
    }

    override fun onCreate() {
        super.onCreate()

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}