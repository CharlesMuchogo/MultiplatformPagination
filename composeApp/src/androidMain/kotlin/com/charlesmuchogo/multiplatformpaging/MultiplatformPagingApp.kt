package com.charlesmuchogo.multiplatformpaging

import android.app.Application
import di.KoinInit
import di.commonModule
import di.platformModule
import org.jetbrains.compose.components.resources.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.logger.Level

class MultiplatformPagingApp: Application() {
    override fun onCreate() {
        super.onCreate()
        KoinInit().init {
            androidLogger(level = if (BuildConfig.DEBUG) Level.ERROR else Level.NONE)
            androidContext(androidContext = this@MultiplatformPagingApp)
            modules(
                listOf(
                    platformModule(),
                    commonModule()
                ),
            )
        }
    }
}