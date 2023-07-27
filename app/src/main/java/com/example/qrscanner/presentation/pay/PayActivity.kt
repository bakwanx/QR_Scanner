package com.example.qrscanner.presentation.pay

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.qrscanner.R
import com.example.qrscanner.presentation.MainActivity
import com.example.qrscanner.presentation.pay.model.PayModel
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.formatDate
import com.example.qrscanner.util.formateTime
import com.example.qrscanner.util.scanText
import com.example.qrscanner.util.toCurrencyFormat
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PayActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val getValue = intent.getStringExtra(KEY_PAY)
        val payModel: PayModel = scanText(getValue.toString())
        setContent {
            QRScannerTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            24.dp
                        ),
                ) {
                    HeaderPay()
                    DatePayment(payModel)
                    Divider(color = Color.Gray, thickness = 1.dp)
                    DetailTransaction(payModel)
                    Spacer(Modifier.weight(1f))
                    ButtonFinish(context = this@PayActivity)
                }
            }
        }
    }

    companion object {
        val KEY_PAY = "key pay"
    }
}

@Composable
fun HeaderPay() {
    Row(
        horizontalArrangement = Arrangement.Center,
        modifier = Modifier.fillMaxWidth()
    ) {
        Box(
            modifier = Modifier
                .height(70.dp)
                .width(100.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_qris),
                contentDescription = "",
            )
        }
    }
}

@Composable
fun DatePayment(payModel: PayModel) {

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(
                bottom = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = formatDate(payModel.date))
        Text(text = "${payModel.bank} || ${formateTime(payModel.date)}")

    }
}

@Composable
fun DetailTransaction(payModel: PayModel) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Detail Transaksi", fontSize = 20.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(
                    top = 16.dp
                )
            )
            Text(
                text = "${payModel.idTransaction}", modifier = Modifier.padding(
                    top = 18.dp
                ),
                fontWeight = FontWeight.Medium
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Toko")
            Text(text = "${payModel.merchant}")
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 8.dp
                ),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Total",
                fontWeight = FontWeight.Medium
            )
            Text(
                text = "${payModel.nominal.toString().toCurrencyFormat()}"
            )
        }

    }
}

@Composable
fun ButtonFinish(context: Context) {
    Button(

        onClick = {

            context.startActivity(Intent(context, MainActivity::class.java))
        },

        modifier = Modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp
            ),


        ) {
        Text(
            text = "Selesai",
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
        )
    }
}