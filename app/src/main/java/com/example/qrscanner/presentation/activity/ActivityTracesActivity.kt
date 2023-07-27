package com.example.qrscanner.presentation.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
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
import androidx.compose.material3.contentColorFor
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.example.qrscanner.presentation.activity.model.DataDonutChartModel
import com.example.qrscanner.presentation.activity.model.DonutChartModel
import com.example.qrscanner.presentation.activity.model.LineChartModel
import com.example.qrscanner.presentation.pay.PayActivity
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.patrykandpatrick.vico.compose.axis.horizontal.bottomAxis
import com.patrykandpatrick.vico.compose.axis.vertical.startAxis
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.views.chart.line.lineChart
import dagger.hilt.android.AndroidEntryPoint
import java.io.Serializable


@AndroidEntryPoint
class ActivityTracesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dataTransaction = getTransaction()
        val dataLineChart = getDataLineChart()

        setContent {
            QRScannerTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                ) {
                    ListTransaction(this@ActivityTracesActivity, dataTransaction)
                }
            }
        }
    }

    private fun getTransaction(): DonutChartModel {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "donutChart.json")

        val gson = Gson()
        val objDonutChartModel = object : TypeToken<DonutChartModel>() {}.type

        val donutChartModel: DonutChartModel = gson.fromJson(jsonFileString, objDonutChartModel)

        return donutChartModel
    }

    private fun getDataLineChart(): LineChartModel {
        val jsonFileString = getJsonDataFromAsset(applicationContext, "lineChart.json")

        val gson = Gson()
        val objLineChartModelModel = object : TypeToken<LineChartModel>() {}.type

        val lineChartModel: LineChartModel = gson.fromJson(jsonFileString, objLineChartModelModel)

        return lineChartModel
    }
}

@Composable
fun ListTransaction(context: Context, dataTransaction: DonutChartModel) {

    LazyColumn() {
        item {
            Text(
                modifier = Modifier.padding(
                    vertical = 8.dp
                ),
                text = "Persentase Transaksi"
            )
        }
        items(dataTransaction.data) {
            TransactionCard(context = context, dataDonutChartModel = it)
        }
    }
}

@Composable
fun TransactionCard(context: Context, dataDonutChartModel: DataDonutChartModel) {

    Box(
        modifier = Modifier
            .padding(
                bottom = 8.dp
            )
            .clickable {
                val intent = Intent(context, DetailActivityTracesActivity::class.java)
                intent.putExtra(
                    DetailActivityTracesActivity.KEY_DETAIL_TRANSACTION,
                    dataDonutChartModel as Serializable
                )
                context.startActivity(intent)
            }
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
                    text = dataDonutChartModel.label,
                    fontWeight = FontWeight.Medium
                )
                Text(
                    text = "${dataDonutChartModel.percentage} %",
                    fontWeight = FontWeight.Bold
                )

            }
        }
    }
}
