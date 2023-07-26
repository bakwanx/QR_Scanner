package com.example.qrscanner.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.graphics.Color
import com.example.qrscanner.presentation.home.Home
import com.example.qrscanner.ui.theme.QRScannerTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRScannerTheme {
                Surface(color = Color.White) {
                    val transactionViewModel by viewModels<TransactionViewModel>()
                    Home(transactionViewModel, this)
                }

            }
        }
    }

}
