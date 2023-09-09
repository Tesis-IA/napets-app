package com.quantumcode.napets.core.di.module

import com.quantumcode.napets.data.repository.home.HomeRepositoryImp
import com.quantumcode.napets.data.repository.home.IHomeRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HomeModule {

    @Binds
    abstract fun provideHomeRepository(homeRepositoryImp: HomeRepositoryImp) : IHomeRepository
}