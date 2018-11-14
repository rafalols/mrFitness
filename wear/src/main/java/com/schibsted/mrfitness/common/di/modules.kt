package com.schibsted.mrfitness.common.di

import com.schibsted.mrfitness.common.controller.SensorController
import org.koin.dsl.module.module

val controllersModule = module {
    factory { SensorController() }
}