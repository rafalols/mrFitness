package com.schibsted.mrfitness.common

import android.app.Application
import com.schibsted.mrfitness.common.di.controllersModule
import org.koin.android.ext.android.startKoin

class WearApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(controllersModule))
    }

}