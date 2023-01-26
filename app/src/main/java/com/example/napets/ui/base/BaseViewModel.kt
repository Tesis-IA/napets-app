package com.example.napets.ui.base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {
    fun launch(
        coroutineContext: CoroutineContext,
        runBLocking: suspend () -> Unit
    ) {
        CoroutineScope(coroutineContext).launch {
            try {
                runBLocking.invoke()
            }catch (e: Exception){
                e.message?.let { error(it) }
            }
        }
    }
}