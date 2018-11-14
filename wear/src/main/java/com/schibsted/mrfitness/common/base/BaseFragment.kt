package com.schibsted.mrfitness.common.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
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
                afterOnCreateView(view)
            } ?: throw IllegalStateException("BaseFragment must have LayoutResId annotation")

    protected abstract fun bindViewModel()

    open fun afterOnCreateView(view: View?) {}

}