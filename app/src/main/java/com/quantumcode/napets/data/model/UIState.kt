package com.quantumcode.napets.data.model

sealed class UIState<T> {
    class Loading<T> : UIState<T>()
    data class Success<T>(val data: T) : UIState<T>()
    data class Error<T>(val message: String) : UIState<T>()
}
