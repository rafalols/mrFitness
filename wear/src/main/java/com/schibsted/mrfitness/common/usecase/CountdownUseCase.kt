package com.schibsted.mrfitness.common.usecase

import com.schibsted.mrfitness.common.extension.applyComputationSchedulers
import io.reactivex.Single
import java.util.concurrent.TimeUnit

class CountdownUseCase {
    fun execute(time: Long, timeUnit: TimeUnit): Single<Unit> = Single
        .just(Unit)
        .delay(time, timeUnit)
        .applyComputationSchedulers()
}