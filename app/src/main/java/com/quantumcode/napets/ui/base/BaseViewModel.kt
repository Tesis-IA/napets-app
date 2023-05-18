package com.quantumcode.napets.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    open var showLoading = false
    fun runBlockingCoroutine(
        coroutineContext: CoroutineContext,
        runBLocking: suspend () -> Unit
    ): Job {
        return CoroutineScope(coroutineContext).launch {
            try {
                runBLocking.invoke()
                showLoading = true
            } catch (e: Exception) {
                e.message?.let { error(it) }
            }
        }
    }
}