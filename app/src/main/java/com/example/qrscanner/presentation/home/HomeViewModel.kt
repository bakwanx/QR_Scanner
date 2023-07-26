package com.example.qrscanner.presentation.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.qrscanner.domain.BalanceUseCase
import com.example.qrscanner.repository.BalanceRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val balanceUseCase: BalanceUseCase
): ViewModel() {
    private val _getBalance = MutableLiveData<Int>()
    val getBalance: LiveData<Int> = _getBalance

    init {
        viewModelScope.launch {
            withContext(Dispatchers.IO){
                getBalance()
            }
        }
    }

    private fun getBalance(){
        _getBalance.postValue(balanceUseCase.getBalance())
    }

}