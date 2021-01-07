package com.debin.challengegan

import android.app.Application
import com.debin.challengegan.di.apiModule
import com.debin.challengegan.di.useCasesModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class GanApp : Application() {
    private val modules = listOf(apiModule, useCasesModule)

    override fun onCreate() {
        super.onCreate()
        startKoin {
          androidContext(applicationContext)
          modules(modules)
        }
    }
}