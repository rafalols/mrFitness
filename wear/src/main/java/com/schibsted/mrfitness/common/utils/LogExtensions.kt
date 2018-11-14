package com.schibsted.mrfitness.common.utils

import android.util.Log


fun Any.logD(message: String) {
    Log.d(this::class.java.simpleName, message)
}