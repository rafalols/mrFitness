package com.schibsted.mrfitness.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModel
import androidx.wear.ambient.AmbientModeSupport
import com.schibsted.mrfitness.common.utils.LayoutResId

abstract class BaseActivity<VM : ViewModel, VDB : ViewDataBinding> : AppCompatActivity(),
    AmbientModeSupport.AmbientCallbackProvider {

    override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = AmbientCallback()

    protected abstract val viewModel: VM
    lateinit var binding: VDB
        private set

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        (this::class.annotations
            .find { it is LayoutResId } as? LayoutResId)
            ?.let {
                binding = DataBindingUtil.setContentView(this, it.resId)
                binding.setLifecycleOwner(this)
                bindViewModel()
                binding.root
            } ?: throw IllegalStateException("Fragment must have LayoutResId annotation")

        AmbientModeSupport.attach(this)
    }

    protected abstract fun bindViewModel()

}