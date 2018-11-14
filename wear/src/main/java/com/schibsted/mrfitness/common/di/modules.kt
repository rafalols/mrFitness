package com.schibsted.mrfitness.common.di

import com.schibsted.mrfitness.common.usecase.CountdownUseCase
import com.schibsted.mrfitness.features.login.LoginViewModel
import com.schibsted.mrfitness.features.splash.SplashViewModel
import com.schibsted.mrfitness.features.splash.usecases.VerifyUserUseCase
import com.schibsted.mrfitness.features.training.TrainingViewModel
import org.koin.androidx.viewmodel.ext.koin.viewModel
import org.koin.dsl.module.module

val controllersModule = module {

}


val uiModule = module {
    single { VerifyUserUseCase() }
    single { CountdownUseCase() }
    viewModel { TrainingViewModel() }
    viewModel { SplashViewModel(get(), get()) }
    viewModel { LoginViewModel() }
}