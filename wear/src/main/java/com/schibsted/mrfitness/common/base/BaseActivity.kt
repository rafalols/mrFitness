package com.schibsted.mrfitness.common.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LifecycleRegistry
import androidx.lifecycle.ViewModel
import androidx.wear.ambient.AmbientModeSupport
import com.schibsted.mrfitness.common.utils.LayoutResId

abstract class BaseActivity<VM: ViewModel, VDB: ViewDataBinding>: AppCompatActivity(), LifecycleOwner, AmbientModeSupport.AmbientCallbackProvider {

     override fun getAmbientCallback(): AmbientModeSupport.AmbientCallback = AmbientCallback()

     private val mLifecycleRegistry: LifecycleRegistry by lazy { LifecycleRegistry(this) }

    protected abstract val viewModel: VM
    lateinit var binding: VDB
        private set

    final override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mLifecycleRegistry.markState(Lifecycle.State.CREATED)
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

    override fun onStart() {
        super.onStart()
        mLifecycleRegistry.markState(Lifecycle.State.STARTED)
    }

    override fun getLifecycle(): Lifecycle = mLifecycleRegistry


    protected abstract fun bindViewModel()

}