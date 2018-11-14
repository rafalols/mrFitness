package com.schibsted.mrfitness.common.extension

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

fun <T> Single<T>.applyComputationSchedulers(): Single<T> {
    return this.compose { upstream ->
        upstream
            .subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
    }
}

fun <T> Single<T>.applyIoSchedulers(): Single<T> {
    return this.compose { upstream ->
        upstream
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}