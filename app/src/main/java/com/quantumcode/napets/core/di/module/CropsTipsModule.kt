package com.quantumcode.napets.core.di.module

import com.quantumcode.napets.data.repository.cropsTips.CropsTipsRepositoryImp
import com.quantumcode.napets.data.repository.cropsTips.ICropsTipsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
abstract class CropsTipsModule {

    @Binds
    abstract fun provideCropsTips(cropsTipsRepositoryImp: CropsTipsRepositoryImp) : ICropsTipsRepository
}
