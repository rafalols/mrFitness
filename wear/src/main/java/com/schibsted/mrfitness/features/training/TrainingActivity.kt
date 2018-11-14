package com.schibsted.mrfitness.features.training

import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseActivity
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.ActivityTrainingBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutResId(R.layout.activity_training)
class TrainingActivity: BaseActivity<TrainingViewModel, ActivityTrainingBinding>() {

    override val viewModel: TrainingViewModel by viewModel()

    override fun bindViewModel() {

    }


}