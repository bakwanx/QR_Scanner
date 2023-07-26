package com.example.qrscanner.presentation.history

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrscanner.presentation.TransactionViewModel
import com.example.qrscanner.presentation.scan.model.PayModel
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.formatDate
import com.example.qrscanner.util.toCurrencyFormat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HistoryActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            QRScannerTheme {
                val transactionViewModel by viewModels<TransactionViewModel>()
                transactionViewModel.getHistory()
                val historyModels by transactionViewModel.getHistoryModels.observeAsState()

                LazyColumn(
                    modifier = Modifier.fillMaxWidth().padding(24.dp),
                ){
                    if(historyModels != null) {
                        items(historyModels!!) {
                            HistoryCard(payModel = it)
                        }
                    }
                }
            }
        }
    }

}


@Composable
fun HistoryCard(payModel: PayModel) {

    Box {
        Card(
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = payModel.merchant,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = payModel.nominal.toString().toCurrencyFormat(),
                    fontWeight = FontWeight.Bold
                )

            }
            Text(
                text = formatDate(payModel.date),
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp),
            )
        }
    }
}