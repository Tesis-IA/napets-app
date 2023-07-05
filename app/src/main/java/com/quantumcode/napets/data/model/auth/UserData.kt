package com.quantumcode.napets.data.model.auth

import android.os.Parcelable
import com.quantumcode.napets.data.domainmodel.user.UserResponse
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@Parcelize
@JsonClass(generateAdapter = true)
data class UserData(
    val id: Int,
    val username: String,
    val email: String,
    val token: String,
    val password: String
) : Parcelable {
    constructor(userResponse: UserResponse) : this(
        id = userResponse.id ?: 0,
        username = userResponse.username ?: "",
        email = userResponse.email ?: "",
        token = userResponse.token ?: "",
        password = userResponse.password ?: ""
    )
}
