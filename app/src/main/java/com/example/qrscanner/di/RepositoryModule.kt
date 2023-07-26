package com.example.qrscanner.di

import com.example.qrscanner.repository.balance_repository.BalanceRepository
import com.example.qrscanner.repository.balance_repository.BalanceRepositoryImpl
import com.example.qrscanner.repository.database.BalanceDataSource
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
    fun providesBalanceDataSource(): BalanceDataSource = BalanceDataSource()


    @Provides
    @Singleton
    fun providesBalanceRepository (
        balanceRepositoryImpl: BalanceRepositoryImpl
    ): BalanceRepository = balanceRepositoryImpl

}

