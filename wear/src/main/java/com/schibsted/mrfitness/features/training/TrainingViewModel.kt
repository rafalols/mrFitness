package com.schibsted.mrfitness.features.training

import android.hardware.SensorManager
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import com.schibsted.mrfitness.common.base.BaseViewModel
import com.schibsted.mrfitness.common.controller.SensorController
import com.schibsted.mrfitness.common.utils.logD
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import java.util.concurrent.TimeUnit

class TrainingViewModel : BaseViewModel() {


    private val mutableListZ = mutableListOf<Float>()

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
    val counter = MutableLiveData<Double>()
    val stringCounter: LiveData<String>

    init {
        counter.value = 0.0
        stringCounter = Transformations.map(counter) {
            it.toInt().toString()
        }
    }

    private fun initSensor() {
        sensorController
            ?.observeGravityEvent()
            ?.subscribeOn(Schedulers.io())
            ?.debounce(10L, TimeUnit.MILLISECONDS)
            ?.observeOn(AndroidSchedulers.mainThread())
            ?.subscribe {
                gravity.value = it.values[2].toString()

                mutableListZ.add(it.values[2])
                val min = mutableListZ.min()
                val max = mutableListZ.max()
                if (mutableListZ.size == 101){
                    mutableListZ.removeAt(0)
                }
                    if (min != null && max != null && max.minus(min) > 10f){
                            counter.value = counter.value?.plus(0.5)
                        mutableListZ.clear()
                        }
                logD("sensor: AAAA${it.sensor.type} ${it.values?.contentToString()}")
            }?.addTo(disposables)
    }


}