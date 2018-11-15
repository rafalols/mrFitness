package com.schibsted.mrfitness.features.finish

import androidx.navigation.fragment.findNavController
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseFragment
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.FragmentFinishBinding
import org.koin.androidx.viewmodel.ext.android.viewModel

@LayoutResId(R.layout.fragment_finish)
class FinishFragment: BaseFragment<FinishViewModel, FragmentFinishBinding>() {

    val repeats: Int by lazy { arguments?.getInt("repeats") ?: -1 }
    val repeatsGoal: Int by lazy { arguments?.getInt("repeatsGoal") ?: -1 }
    val time: Int by lazy { arguments?.getInt("time") ?: -1 }

    override val viewModel: FinishViewModel by viewModel()

    override fun bindViewModel() {
        binding.viewModel = viewModel
        viewModel.repeats = repeats
        viewModel.repeatsGoal = repeatsGoal
        viewModel.time = time

        viewModel.goBackToStart.observeEvent {
            findNavController().navigate(R.id.action_finishFragment_to_startFragment)
        }
    }
}