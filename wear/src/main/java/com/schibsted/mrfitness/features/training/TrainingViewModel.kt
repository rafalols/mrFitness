package com.schibsted.mrfitness.features.training

import android.hardware.SensorManager
import androidx.lifecycle.MutableLiveData
import com.schibsted.mrfitness.common.base.BaseViewModel
import com.schibsted.mrfitness.common.controller.SensorController
import com.schibsted.mrfitness.common.utils.logD
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TrainingViewModel() : BaseViewModel() {

    var sensorManager: SensorManager? = null
        set(value) {
            field = value
            value?.also { sensorController = SensorController(it) }
        }

    private var sensorController: SensorController? = null
        set(value) {
            field = value
            initSensor()
        }

    val gravity = MutableLiveData<String>()
    val acceleration = MutableLiveData<String>()

    private fun initSensor() {
        sensorController
            ?.observeGravityEvent()
            ?.subscribeOn(Schedulers.io())
            ?.debounce(50L, TimeUnit.MILLISECONDS)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
                it.sensor.type
                gravity.value = it.values?.contentToString()
                logD("sensor: AAAA${it.sensor.type} ${it.values?.contentToString()}")
            }?.addTo(disposables)
    }


}