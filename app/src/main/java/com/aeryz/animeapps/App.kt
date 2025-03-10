package com.aeryz.animeapps

import android.app.Application
import com.aeryz.core.di.coreModule
import com.aeryz.animeapps.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
   override fun onCreate() {
      super.onCreate()
      startKoin {
         androidLogger()
         androidContext(this@App)
         modules(listOf(coreModule, appModule))
      }
   }
}