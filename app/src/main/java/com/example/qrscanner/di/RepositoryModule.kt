package com.example.qrscanner.di

import com.example.qrscanner.repository.BalanceRepository
import com.example.qrscanner.repository.BalanceRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.ExperimentalCoroutinesApi
import javax.inject.Singleton

@ExperimentalCoroutinesApi
@Module
@InstallIn(SingletonComponent::class)
class RepositoryModule {

    @Provides
    @Singleton
    fun providesBalanceRepository (
        balanceRepositoryImpl: BalanceRepositoryImpl
    ): BalanceRepository = balanceRepositoryImpl

}

