package com.example.napets.ui.base

import android.view.View
import android.view.View.*
import androidx.databinding.BindingAdapter
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    open var showLoading = false
    suspend fun runBlockingCoroutine(
        coroutineContext: CoroutineContext,
        runBLocking: suspend () -> Unit
    ) {
        withContext(coroutineContext) {
            try {
                runBLocking.invoke()
                showLoading = true
            }catch (e: Exception){
                e.message?.let { error(it) }
            }
        }
    }
}