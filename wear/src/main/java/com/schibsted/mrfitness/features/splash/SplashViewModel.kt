package com.schibsted.mrfitness.features.splash

import androidx.lifecycle.MutableLiveData
import com.schibsted.mrfitness.common.base.BaseViewModel
import com.schibsted.mrfitness.common.usecase.CountdownUseCase
import com.schibsted.mrfitness.common.utils.Event
import io.reactivex.rxkotlin.addTo
import java.util.concurrent.TimeUnit

class SplashViewModel(
    countdownUseCase: CountdownUseCase
) : BaseViewModel() {

    private val splashDuration = 1500L
    val goToStartEvent: MutableLiveData<Event<Unit>> = MutableLiveData()

    init {
        countdownUseCase
            .execute(splashDuration, TimeUnit.MILLISECONDS)
            .subscribe { _ -> goToStartEvent.value = Event(Unit) }
            .addTo(disposables)
    }

}