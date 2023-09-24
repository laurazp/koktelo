package com.luridevlabs.koktelo

import android.app.Application
import com.luridevlabs.koktelo.di.baseModule
import com.luridevlabs.koktelo.di.charactersModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class KokteloApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@KokteloApplication)
            modules(listOf(baseModule, charactersModule))
            allowOverride(true)
        }
    }


}