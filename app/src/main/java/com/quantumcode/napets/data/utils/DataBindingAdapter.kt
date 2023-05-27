package com.quantumcode.napets.data.utils

import android.view.View
import androidx.databinding.BindingAdapter

object DataBindingAdapter {
    @BindingAdapter("visibility")
    @JvmStatic
    fun setIsVisible(view: View, visibility: Boolean){
        when(visibility){
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }
}