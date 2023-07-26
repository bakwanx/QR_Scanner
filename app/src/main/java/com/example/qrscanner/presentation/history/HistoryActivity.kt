package com.example.qrscanner.presentation.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import com.example.qrscanner.presentation.BalanceViewModel
import com.example.qrscanner.presentation.home.Home
import com.example.qrscanner.ui.theme.QRScannerTheme
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRScannerTheme {

            }
        }
    }
}