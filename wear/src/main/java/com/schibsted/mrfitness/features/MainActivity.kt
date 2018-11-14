package com.schibsted.mrfitness.features

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.wear.ambient.AmbientModeSupport
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.AmbientCallback

class MainActivity : AppCompatActivity(), AmbientModeSupport.AmbientCallbackProvider {

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = AmbientCallback()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    override fun onSupportNavigateUp()
            = findNavController(R.id.nav_host).navigateUp()

}
