package com.example.qrscanner.domain

import com.example.qrscanner.repository.BalanceRepository
import javax.inject.Inject

class BalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository
) {

    fun doTransaction(bill: Int): Boolean {
        val totalBalance = bill - balanceRepository.getBalance()
        if(totalBalance < 0) return false
        balanceRepository.setBalance(totalBalance)
        return true
    }

    fun getBalance(): Int {
        return balanceRepository.getBalance()
    }
}