package com.schibsted.mrfitness.features.training

import android.content.Context
import android.hardware.SensorManager
import androidx.navigation.fragment.findNavController
import com.schibsted.mrfitness.R
import com.schibsted.mrfitness.common.base.BaseFragment
import com.schibsted.mrfitness.common.utils.LayoutResId
import com.schibsted.mrfitness.databinding.FragmentTrainingBinding
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.addTo
import io.reactivex.schedulers.Schedulers
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.util.concurrent.TimeUnit

@LayoutResId(R.layout.fragment_training)
class TrainingFragment: BaseFragment<TrainingViewModel, FragmentTrainingBinding>() {


    private val repeats: Int by lazy { arguments?.getInt("repeats") ?: 30 }
    private val time: Int by lazy { arguments?.getInt("time") ?: 30 }

    override val viewModel: TrainingViewModel by viewModel()

    override fun bindViewModel() {
        binding.viewModel = viewModel
        viewModel.sensorManager = activity?.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        viewModel.repeats = repeats

        viewModel.stringCounter.observe {
            binding.counterText.text = getString(R.string.counter_text, it, repeats)
        }

        Observable.interval(1, TimeUnit.SECONDS)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                binding.remainingTime.text = getString(R.string.remaining_text, time - it)

                if (it.toInt() == time) {
                    TrainingFragmentDirections.actionTrainingFragmentToFinishFragment()
                        .apply {
                            viewModel.counter.value?.also { counter -> setRepeats(counter.toInt()) }
                            setRepeatsGoal(repeats)
                            setTime(time)
                        }
                        .also { action -> findNavController().navigate(action) }
                }

            }.addTo(viewModel.disposables)

    }



}