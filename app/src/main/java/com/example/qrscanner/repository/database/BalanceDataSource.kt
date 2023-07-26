package com.example.qrscanner.repository.database

class BalanceDataSource {

    private var db : Int = 100000

    fun setBalance(balance: Int)
    {
        db = balance
    }

    fun getBalance(): Int = db

}