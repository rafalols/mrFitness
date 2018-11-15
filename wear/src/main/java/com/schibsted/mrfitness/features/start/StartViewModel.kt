package com.schibsted.mrfitness.features.start

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.schibsted.mrfitness.common.base.BaseViewModel
import com.schibsted.mrfitness.common.controller.FirebaseController
import com.schibsted.mrfitness.common.model.NextSerie
import com.schibsted.mrfitness.common.utils.Event
import com.schibsted.mrfitness.common.utils.logD
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers

class StartViewModel(): BaseViewModel() {

    val nextSerie = MutableLiveData<NextSerie>()
    val goToStartTraining = MutableLiveData<Event<Unit>>()

    init {
        FirebaseController(FirebaseDatabase.getInstance())
            .getNextSerie()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { serie ->
                logD("Serie duration = ${serie.newDuration}, interval = ${serie.newInterval}, repeats = ${serie.newRepeats}")
                nextSerie.value = serie
            }.addTo(disposables)
    }


    fun start() {
        goToStartTraining.value = Event(Unit)
    }

}