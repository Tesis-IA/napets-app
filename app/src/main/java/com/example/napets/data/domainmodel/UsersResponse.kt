package com.example.napets.data.domainmodel


import android.os.Parcelable
import com.example.napets.data.model.UserData
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UsersResponse(
    val allUsers: List<UserData> = emptyList()
): Parcelable