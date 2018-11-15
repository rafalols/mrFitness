package com.schibsted.mrfitness.features.start

import android.view.View
import androidx.navigation.fragment.FragmentNavigator
import androidx.navigation.fragment.findNavController
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseFragment
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.FragmentStartBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutResId(R.layout.fragment_start)
class StartFragment: BaseFragment<StartViewModel, FragmentStartBinding>() {

    override val viewModel: StartViewModel by viewModel()

    override fun bindViewModel() {
        binding.viewModel = viewModel
    }

    override fun start(view: View?) {
        super.start(view)

        viewModel.nextSerie.observe{
            binding.nextTrainingText.text = getString(R.string.next_training_text, it?.newRepeats.toString() , it?.newDuration.toString())
        }

        viewModel.goToStartTraining.observeEvent { _ ->
            viewModel.nextSerie.value.let { serie ->
                StartFragmentDirections.actionStartFragmentToTrainingFragment().apply {
                        serie?.newRepeats?.also { setRepeats(it) }
                        serie?.newDuration?.also { setTime(it) }
                    }
            }.also { findNavController().navigate(it) }
        }
    }

}