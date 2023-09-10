package com.quantumcode.napets.data.model.home

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Subject(
    val id: Int,
    val title: String,
    val image: String
) : Parcelable
