package com.example.qrscanner.repo_balance_impl_test

import com.example.qrscanner.domain.BalanceUseCase
import com.example.qrscanner.repository.balance_repository.BalanceRepository
import com.example.qrscanner.repository.balance_repository.BalanceRepositoryImpl
import com.example.qrscanner.repository.database.BalanceDataSource
import io.mockk.coVerify
import io.mockk.mockk
import io.mockk.slot
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class BalanceRepositoryTest {
    private val balanceDataSourceMock = mockk<BalanceDataSource>(relaxed = true)
    private val balanceRepositoryImpl = BalanceRepositoryImpl(balanceDataSourceMock)
    private val balanceUseCaseMock = mockk<BalanceRepository>(relaxed = true)
    private val balanceUseCase = BalanceUseCase(balanceUseCaseMock)

//    @Before
//    fun setup(){
//        balanceRepositoryImpl.setBalance(100000)
//    }

    @Test
    fun `verifikasi saldo yang dimasukkan dengan yang di simpan`() = runTest {
        val balanceRepositoryImpl = BalanceRepositoryImpl(balanceDataSourceMock)
        balanceRepositoryImpl.setBalance(100000)
        val balanceUseCase = BalanceUseCase(balanceRepositoryImpl)

        print(balanceRepositoryImpl.getBalance())
//        balanceRepositoryImpl.setBalance(balance)

//        Assert.assertEquals(100000, balanceRepositoryImpl.getBalance())
    }

    @Test
    fun `verifikasi saldo kurang`() = runTest {
        val balance = 100000
        val bill = 500000000
        balanceRepositoryImpl.setBalance(balance)
        val result = balanceUseCase.doTransaction(bill)
//        print("pesan ${result}")


        Assert.assertEquals(false, result)
    }
}