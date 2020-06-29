package com.example.lecione

import android.app.Application
import com.example.lecione.di.daoModule
import com.example.lecione.di.databaseModule
import com.example.lecione.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class AppApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@AppApplication)
            modules(
                listOf(
                    databaseModule,
                    daoModule,
                    viewModelModule
                )
            )
        }
    }
}