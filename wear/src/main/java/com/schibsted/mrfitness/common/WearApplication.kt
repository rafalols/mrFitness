package com.schibsted.mrfitness.common

import android.app.Application
import com.schibsted.mrfitness.common.di.controllersModule
import com.schibsted.mrfitness.common.di.uiModule
import org.koin.android.ext.android.startKoin

class WearApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(controllersModule, uiModule))
    }

}