package com.quantumcode.napets.data.repository.sharedpreferences

interface ISharedPreferencesRepository {
    fun setValue(key: String, value: String)

    fun clearValue(key: String)

    fun clearAll()
}