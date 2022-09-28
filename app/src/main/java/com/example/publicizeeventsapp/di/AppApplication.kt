package com.example.publicizeeventsapp.di

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            listOf(modules(networkModules, dataModules, domainModules, presentationModules))
        }
    }
}