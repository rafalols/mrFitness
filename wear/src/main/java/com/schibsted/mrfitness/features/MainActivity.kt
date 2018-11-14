package com.schibsted.mrfitness.features

import android.content.Intent
import android.os.Bundle
import android.support.wearable.activity.WearableActivity
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.features.training.TrainingActivity

class MainActivity : WearableActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Enables Always-on
        setAmbientEnabled()

        Intent(this, TrainingActivity::class.java)
            .also { startActivity(it) }

    }

}
