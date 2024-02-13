package com.quantumcode.napets.core.di.module

import com.quantumcode.napets.data.repository.prediction.IPredictionRepository
import com.quantumcode.napets.data.repository.prediction.PredictionRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PredictionModule {

    @Binds
    abstract fun providePredictionRepository(predictionRepositoryImp: PredictionRepositoryImp) : IPredictionRepository
}
