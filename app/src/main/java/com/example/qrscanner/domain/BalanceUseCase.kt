package com.example.qrscanner.domain

import com.example.qrscanner.repository.balance_repository.BalanceRepository
import javax.inject.Inject

class BalanceUseCase @Inject constructor(
    private val balanceRepository: BalanceRepository
) {

    fun doTransaction(bill: Int): Boolean {

        if(balanceRepository.getBalance() < bill) return false
        val totalBalance = balanceRepository.getBalance() - bill
        balanceRepository.setBalance(totalBalance)

        return true
    }



    fun getBalance(): Int {
        return balanceRepository.getBalance()
    }
}