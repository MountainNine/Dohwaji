package com.mtnine.dohwaji.base

import android.app.Application
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

@HiltAndroidApp
class BaseApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob())
    override fun onCreate() {
        super.onCreate()
    }

}