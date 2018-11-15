package com.schibsted.mrfitness.features.finish

import androidx.lifecycle.MutableLiveData
import com.google.firebase.database.FirebaseDatabase
import com.schibsted.mrfitness.common.base.BaseViewModel
import com.schibsted.mrfitness.common.controller.FirebaseController
import com.schibsted.mrfitness.common.model.FinishedSerie
import com.schibsted.mrfitness.common.utils.Event
import io.reactivex.rxkotlin.addTo

class FinishViewModel(): BaseViewModel() {

    val goBackToStart = MutableLiveData<Event<Unit>>()

    var repeats: Int = -1
    var repeatsGoal: Int = -1
    var time: Int = -1
    var hr = 120

    fun finish() {
        FirebaseController(FirebaseDatabase.getInstance())
            .sendFinishedSerie(FinishedSerie(
                duration = time,
                interval = 30,
                repeats = repeats,
                repeatsGoal = repeatsGoal,
                finalPulse = hr
            ))
            .subscribe { goBackToStart.value = Event(Unit) }
            .addTo(disposables)

    }

}