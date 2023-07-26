package com.example.qrscanner.repository.balance_repository

import com.example.qrscanner.repository.database.BalanceDataSource
import javax.inject.Inject

class BalanceRepositoryImpl @Inject constructor(
    private val balanceDataSource: BalanceDataSource
) : BalanceRepository {

    override fun getBalance(): Int {
        return balanceDataSource.getBalance()
    }

    override fun setBalance(balance: Int) {
        balanceDataSource.setBalance(balance)
    }
//
}