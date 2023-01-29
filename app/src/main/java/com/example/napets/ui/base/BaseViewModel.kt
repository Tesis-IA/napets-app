package com.example.napets.ui.base

import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    open var showLoading = View.GONE

    fun runBlockingCoroutine(
        coroutineContext: CoroutineContext,
        runBLocking: suspend () -> Unit
    ) {
        CoroutineScope(coroutineContext).launch {
            try {
                runBLocking.invoke()
                showLoading = View.VISIBLE
            }catch (e: Exception){
                e.message?.let { error(it) }
            }
        }
    }
}