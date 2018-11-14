package com.schibsted.mrfitness.features.splash

import android.view.View
import androidx.navigation.fragment.findNavController
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseFragment
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.FragmentSplashBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutResId(R.layout.fragment_splash)
class SplashFragment : BaseFragment<SplashViewModel, FragmentSplashBinding>() {
    override val viewModel: SplashViewModel by viewModel()

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun start(view: View?) {
        viewModel.goToTrainingEvent.observeEvent { findNavController().navigate(R.id.action_splashFragment_to_trainingFragment) }
        viewModel.goToLoginEvent.observeEvent { findNavController().navigate(R.id.action_splashFragment_to_loginFragment) }
    }
}