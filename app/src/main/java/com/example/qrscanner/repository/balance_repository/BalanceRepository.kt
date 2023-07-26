package com.example.qrscanner.repository.balance_repository

interface BalanceRepository {

    fun getBalance(): Int

    fun setBalance(balance: Int)
}