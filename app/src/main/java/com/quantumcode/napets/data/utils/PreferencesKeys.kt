package com.quantumcode.napets.data.utils

import androidx.datastore.preferences.core.stringPreferencesKey

object PreferencesKeys {
    val KEY = stringPreferencesKey("key")
    val EMAIL = stringPreferencesKey("email")
    val USER_ID = stringPreferencesKey("user_id")
    val USERNAME = stringPreferencesKey("username")
    val DEVICE_ID = stringPreferencesKey("device_id")
}
