package com.schibsted.mrfitness.common.model

import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class FinishedSerie (
    val duration: Int? = null,
    val interval: Int? = null,
    val repeats: Int? = null,
    val finalPulse: Int? = null,
    val repeatsGoal: Int? = null
) {


}