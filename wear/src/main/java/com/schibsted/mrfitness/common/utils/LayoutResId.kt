package com.schibsted.mrfitness.common.utils

import androidx.annotation.LayoutRes

@Target(AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LayoutResId(@LayoutRes val resId: Int)