package com.example.qrscanner.repository

import com.example.qrscanner.repository.database.DataSource

class BalanceRepositoryImpl constructor(
    private val dataSource: DataSource
) : BalanceRepository {

    override fun getBalance(): Int {
        return dataSource.getBalance()
    }

    override fun setBalance(balance: Int) {
        dataSource.setBalance(balance)
    }
}