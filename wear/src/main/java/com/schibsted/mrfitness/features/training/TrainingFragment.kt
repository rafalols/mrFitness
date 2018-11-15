package com.schibsted.mrfitness.features.training

import android.content.Context
import android.hardware.SensorManager
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseFragment
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.FragmentTrainingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutResId(R.layout.fragment_training)
class TrainingFragment: BaseFragment<TrainingViewModel, FragmentTrainingBinding>() {

    override val viewModel: TrainingViewModel by viewModel()

    override fun bindViewModel() {
        binding.viewModel = viewModel
        viewModel.sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    }

}