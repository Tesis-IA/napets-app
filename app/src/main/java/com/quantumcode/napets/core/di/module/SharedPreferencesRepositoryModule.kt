package com.quantumcode.napets.core.di.module

import android.app.Application
import com.quantumcode.napets.data.repository.sharedpreferences.ISharedPreferencesRepository
import com.quantumcode.napets.data.repository.sharedpreferences.SharedPreferencesRepositoryImp
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class SharedPreferencesRepositoryModule {

    @Binds
    abstract fun provideSharedPreferencesRepository(sharedPreferencesRepositoryImp: SharedPreferencesRepositoryImp) : ISharedPreferencesRepository
}