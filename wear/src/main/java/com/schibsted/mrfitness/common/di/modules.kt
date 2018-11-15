package com.schibsted.mrfitness.common.di

import com.google.firebase.database.FirebaseDatabase
import com.schibsted.mrfitness.common.controller.FirebaseController
import com.schibsted.mrfitness.common.usecase.CountdownUseCase
import com.schibsted.mrfitness.features.login.LoginViewModel
import com.schibsted.mrfitness.features.splash.SplashViewModel
import com.schibsted.mrfitness.features.start.StartViewModel
import com.schibsted.mrfitness.features.training.TrainingViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val controllersModule = module {
    factory { FirebaseDatabase.getInstance() }
    factory { FirebaseController(get())}
}


val uiModule = module {
    single { CountdownUseCase() }
    viewModel { TrainingViewModel() }
    viewModel { SplashViewModel(get()) }
    viewModel { LoginViewModel() }
    viewModel { StartViewModel() }
}