package com.quantumcode.napets.data.repository.home

import com.quantumcode.napets.R
import com.quantumcode.napets.data.model.home.Subject
import javax.inject.Inject

class HomeRepositoryImp @Inject constructor() : IHomeRepository {
    override suspend fun getSubjects() = listOf(
        Subject(
            1, "Historial", R.drawable.ic_history_home
        ),
        Subject(
            2, "Plagas y Enfermedades", R.drawable.ic_pests
        ),
        Subject(
            3, "Consejos de Cultivos", R.drawable.ic_tips
        )
    )
}
