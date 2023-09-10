package com.quantumcode.napets.data.repository.home

import com.quantumcode.napets.core.service.ApiService
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor(
    private val apiService: ApiService
) : IHomeRepository {
    override suspend fun getSubjects() = apiService.getSubjects().body().orEmpty()
}
