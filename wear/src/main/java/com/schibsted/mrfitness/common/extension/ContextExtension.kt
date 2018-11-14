package com.schibsted.mrfitness.common.extension

import android.content.Context
import android.widget.Toast

fun Context.toast(message: CharSequence) =
    Toast.makeText(this, message, Toast.LENGTH_SHORT).show()

fun androidx.fragment.app.Fragment.toast(message: CharSequence) =
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()