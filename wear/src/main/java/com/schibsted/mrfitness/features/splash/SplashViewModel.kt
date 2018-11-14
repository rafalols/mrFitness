package com.schibsted.mrfitness.features.splash

import androidx.lifecycle.MutableLiveData
import com.schibsted.mrfitness.common.base.BaseViewModel
import com.schibsted.mrfitness.common.usecase.CountdownUseCase
import com.schibsted.mrfitness.common.utils.Event
import com.schibsted.mrfitness.features.splash.usecases.VerifyUserUseCase
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SplashViewModel(
    countdownUseCase: CountdownUseCase,
    private val verifyUserUseCase: VerifyUserUseCase)
    : BaseViewModel() {

    private val splashDuration = 500L
    val goToLoginEvent: MutableLiveData<Event<Unit>> = MutableLiveData()
    val goToTrainingEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

init {
    countdownUseCase
        .execute(splashDuration, TimeUnit.MILLISECONDS)
        .subscribe { _ -> checkIsUserAuthenticated() }
        .addTo(disposables)
}

    private fun checkIsUserAuthenticated() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}