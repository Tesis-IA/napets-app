package com.quantumcode.napets.ui.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import java.lang.Exception
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel : ViewModel() {

    open var showLoading = false

    private val _errorResponse = MutableLiveData<String>()
    val errorResponse get() = _errorResponse

    fun runBlockingCoroutine(
        coroutineContext: CoroutineContext,
        runBLocking: suspend () -> Unit
    ): Job {
        return CoroutineScope(coroutineContext).launch {
            try {
                showLoading = true
                runBLocking.invoke()
            } catch (e: Exception) {
                e.message?.let { error(it) }
            }
        }
    }

    open fun handleErrorResponse(message: String) {
        _errorResponse.postValue(message)
    }
}
