package com.quantumcode.napets.data.repository.home

import com.quantumcode.napets.data.model.home.Subject

interface IHomeRepository {

    suspend fun getSubjects(): List<Subject>
}
