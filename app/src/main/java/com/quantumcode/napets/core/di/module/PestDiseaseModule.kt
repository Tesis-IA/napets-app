package com.quantumcode.napets.core.di.module

import com.quantumcode.napets.data.repository.pestDisease.IPestDiseaseRepository
import com.quantumcode.napets.data.repository.pestDisease.PestDiseaseRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class PestDiseaseModule {

    @Binds
    abstract fun providePestDisease(pestDiseaseRepositoryImp: PestDiseaseRepositoryImp) : IPestDiseaseRepository
}