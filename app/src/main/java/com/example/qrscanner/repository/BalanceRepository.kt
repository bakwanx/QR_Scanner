package com.example.qrscanner.repository

interface BalanceRepository {

    fun getBalance(): Int

    fun setBalance(balance: Int)
}