package com.example.qrscanner.presentation.activity

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.qrscanner.presentation.activity.model.DataDonutChartModel
import com.example.qrscanner.presentation.activity.model.LabelDetailModel
import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.presentation.promo_detail.PromoDetailActivity
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.toCurrencyFormat
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DetailActivityTracesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataDonutChartModel =  intent.getSerializableExtra(KEY_DETAIL_TRANSACTION) as DataDonutChartModel


        setContent {
            QRScannerTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                ) {
                    Header()
                    ListHistory(dataDonutChartModel = dataDonutChartModel)
                }
            }
        }
    }

    companion object {
        val KEY_DETAIL_TRANSACTION = "key detail transaction"
    }
}

@Composable
fun Header(){
    Text(
        text = "History",
        modifier = Modifier.padding(
            vertical = 10.dp
        )
    )
}

@Composable
fun ListHistory(dataDonutChartModel: DataDonutChartModel){
    LazyColumn(){
        items(dataDonutChartModel.data){
            TransactionDetailCard(labelDetailModel = it)
        }
    }
}

@Composable
fun TransactionDetailCard(labelDetailModel: LabelDetailModel) {

    Box(
        modifier = Modifier.padding(
            bottom = 8.dp
        )
    ) {
        Card(
            border = BorderStroke(0.5.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 10.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = labelDetailModel.trx_date,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = labelDetailModel.nominal.toString().toCurrencyFormat(),
                    fontWeight = FontWeight.Bold
                )



            }
        }
    }
}