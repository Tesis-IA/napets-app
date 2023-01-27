package com.example.napets.core.di

import com.example.napets.data.repository.AuthenticationRepositoryImp
import com.example.napets.data.repository.IAuthenticationRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class AuthenticationModule {

    @Binds
    abstract fun provideAuthenticationRepository(authenticationRepositoryImp: AuthenticationRepositoryImp) : IAuthenticationRepository

}