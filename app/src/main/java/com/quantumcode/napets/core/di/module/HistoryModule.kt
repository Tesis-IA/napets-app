package com.quantumcode.napets.core.di.module

import com.quantumcode.napets.data.repository.history.HistoryRepositoryImp
import com.quantumcode.napets.data.repository.history.IHistoryRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class HistoryModule {

    @Binds
    abstract fun provideHistoryRepository(historyRepositoryImp: HistoryRepositoryImp) : IHistoryRepository
}
