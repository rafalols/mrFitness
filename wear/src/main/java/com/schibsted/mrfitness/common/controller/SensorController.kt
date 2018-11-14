package com.schibsted.mrfitness.common.controller

import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import io.reactivex.Observable
import io.reactivex.subjects.PublishSubject

class SensorController(private val sensorManager: SensorManager): SensorEventListener {

    private val gravitySesnor = sensorManager.getDefaultSensor(Sensor.TYPE_GRAVITY)
    private val accelerometrSesnor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

    private val sensorSubject = PublishSubject.create<SensorEvent>()

    fun observeGravityEvent(): Observable<SensorEvent> {
        return sensorSubject
    }

    init {
        gravitySesnor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
        accelerometrSesnor?.also {
            sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        event?.let { sensorSubject.onNext(it) }
    }


}