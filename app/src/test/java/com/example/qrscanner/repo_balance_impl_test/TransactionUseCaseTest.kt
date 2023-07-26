package com.example.qrscanner.repo_balance_impl_test

import com.example.qrscanner.domain.TransactionUseCase
import com.example.qrscanner.repository.balance_repository.BalanceRepository
import io.mockk.coEvery
import io.mockk.mockk

import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import java.lang.IllegalArgumentException

@ExperimentalCoroutinesApi
class TransactionUseCaseTest {


    private var balanceRepository = mockk<BalanceRepository>()

    private lateinit var transactionUseCase :TransactionUseCase

    @Before
    fun setUp() {
        transactionUseCase = TransactionUseCase(balanceRepository)
    }

    @Test
    fun testSuccessTransaction() {
        coEvery {
            balanceRepository.getBalance()
        } returns 1000
        val balance = transactionUseCase.getBalance()

        Assert.assertEquals(1000, balance)
    }
    @Test(expected = IllegalArgumentException::class)
    fun testZeroBillTransaction(){
        transactionUseCase.doTransaction(0)
    }


    @Test
    fun testNotEnoughBalance(){
        coEvery {
            balanceRepository.setBalance(0)
            balanceRepository.getBalance()
        } returns 100
        val result = transactionUseCase.doTransaction(1000)
        Assert.assertEquals(false, result)
    }




}