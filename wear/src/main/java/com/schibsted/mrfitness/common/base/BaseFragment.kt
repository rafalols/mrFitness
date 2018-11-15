package com.schibsted.mrfitness.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.schibsted.mrfitness.common.utils.Event
import com.schibsted.mrfitness.common.utils.EventObserver
import com.schibsted.mrfitness.common.utils.LayoutResId

abstract class BaseFragment<VM: ViewModel, VDB: ViewDataBinding>: Fragment() {

    protected abstract val viewModel: VM
    lateinit var binding: VDB
        private set

    final override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? =
        (this::class.annotations
            .find { it is LayoutResId } as? LayoutResId)
            ?.let {
                binding = DataBindingUtil.inflate(inflater, it.resId, container, false)
                binding.setLifecycleOwner(this)
                bindViewModel()
                binding.root
            }
            ?.also { view ->
                start(view)
            } ?: throw IllegalStateException("BaseFragment must have LayoutResId annotation")

    protected abstract fun bindViewModel()

    open fun start(view: View?) {}

    fun <T> LiveData<Event<T>>.observeEvent(observe: ((value: T?) -> Unit)) {
        this.observe(this@BaseFragment, EventObserver { value -> observe(value) })
    }

    fun <T> LiveData<T>.observe(observe: ((value: T?) -> Unit)) {
        this.observe(this@BaseFragment, Observer { value -> observe(value) })
    }

}