package com.quantumcode.napets.data.repository.sharedpreferences

import android.content.SharedPreferences
import javax.inject.Inject

class SharedPreferencesRepositoryImp @Inject constructor(
    private val sharedPreferences: SharedPreferences
) : ISharedPreferencesRepository {
    override fun setValue(key: String, value: String) {
        sharedPreferences.edit().putString("token", value).apply()
    }

    override fun clearValue(key: String) {
        sharedPreferences.edit().remove(key).apply()
    }

    override fun clearAll() {
        sharedPreferences.edit().clear().apply()
    }
}