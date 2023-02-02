package com.example.napets.data.utils

import android.view.View
import androidx.databinding.BindingAdapter

object DataBindingAdapter {
    @BindingAdapter("isVisible")
    @JvmStatic
    fun setIsVisible(view: View, visibility: Boolean){
        when(visibility){
            true -> view.visibility = View.VISIBLE
            false -> view.visibility = View.GONE
        }
    }
}