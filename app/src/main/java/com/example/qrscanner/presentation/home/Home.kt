package com.example.qrscanner.presentation.home

import android.content.Context
import android.content.Intent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.qrscanner.R
import com.example.qrscanner.presentation.TransactionViewModel
import com.example.qrscanner.presentation.history.HistoryActivity
import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.presentation.promo_detail.PromoDetailActivity
import com.example.qrscanner.presentation.scan.ScanActivity
import com.example.qrscanner.util.toCurrencyFormat
import java.io.Serializable

@Composable
fun Home(
    transactionViewModel: TransactionViewModel,
    promoViewModel: PromoViewModel,
    context: Context
) {
    val balance by transactionViewModel.getBalance.observeAsState()
    val historyModels by promoViewModel.getPromoModels.observeAsState()

    Column(
        modifier = Modifier
            .verticalScroll(rememberScrollState())
            .background(Color.White)
            .fillMaxHeight()
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Header(balance.toString())
        Action(context)

        if (historyModels != null) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ) {
                historyModels!!.forEach {
                    PromoCard(promoModel = it, context)
                }
            }

        } else {
            Column(
                modifier = Modifier
                    .fillMaxHeight()
                    .fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Loading()
            }

        }

    }

}

@Composable
fun Header(balance: String) {


    Box(
        modifier = Modifier.padding(start = 24.dp, top = 50.dp, end = 24.dp, bottom = 24.dp)
    ) {
        Card(
            border = BorderStroke(1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier
                .fillMaxWidth()

        ) {
            Text(
                text = "Saldo anda",

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 50.dp),
            )
            Text(
                balance.toCurrencyFormat(),
                fontWeight = FontWeight.Bold,
                fontSize = 30.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 12.dp, top = 12.dp, bottom = 50.dp),
            )
        }
    }
}

@Composable
fun Action(context: Context) {
    Row(
        modifier = Modifier.padding(
            16.dp
        ),
        horizontalArrangement = Arrangement.Center
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.clickable {
                context.startActivity(Intent(context, ScanActivity::class.java))
            }
        ) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_scan),
                    contentDescription = "",
                )
            }
            Text(text = "Pembayaran", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {
                    context.startActivity(Intent(context, HistoryActivity::class.java))
                }
        ) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_history),
                    contentDescription = "",
                )
            }
            Text(text = "History", modifier = Modifier.padding(start = 8.dp))
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .padding(start = 8.dp)
                .clickable {

                }
        ) {
            Box(
                modifier = Modifier
                    .height(30.dp)
                    .width(30.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.ic_activity),
                    contentDescription = "",
                )
            }
            Text(text = "Aktivitas", modifier = Modifier.padding(start = 8.dp))
        }
    }
}

@Composable
fun PromoCard(promoModel: PromoModel, context: Context) {

    Box(
        modifier = Modifier
            .padding(
                bottom = 10.dp
            )
            .clickable {
                val intent = Intent(context, PromoDetailActivity::class.java)
                intent.putExtra(PromoDetailActivity.KEY_PROMO_DETAIL, promoModel as Serializable)
                context.startActivity(intent)
            }
    ) {
        Card(
            border = BorderStroke(0.1.dp, Color.Gray),
            colors = CardDefaults.cardColors(containerColor = Color.White),
            modifier = Modifier.padding(
                8.dp
            ),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                if (promoModel.img.formats.imageSize != null) {
                    AsyncImage(
                        model = promoModel.img.formats.imageSize.url,
                        contentDescription = "caption",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 8.dp
                            ),
                        contentScale = ContentScale.Fit,
                    )
                } else {
                    AsyncImage(
                        model = promoModel.img.url,
                        contentDescription = "caption",
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(
                                bottom = 8.dp
                            ),
                        contentScale = ContentScale.Fit,
                    )
                }

                Text(
                    text = promoModel.nama,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    )
                )
                Text(
                    text = promoModel.desc,
                    fontWeight = FontWeight.Light,
                    modifier = Modifier.padding(
                        bottom = 8.dp
                    ),
                    maxLines = 2
                )

            }
            Text(
                text = promoModel.createdAt,
                fontWeight = FontWeight.Light,
                fontSize = 10.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp, start = 8.dp),
            )
        }
    }
}

@Composable
fun Loading() {
    CircularProgressIndicator(
        modifier = Modifier.drawBehind {
            drawCircle(
                Color.Red,
                radius = size.width / 2 - 5.dp.toPx() / 2,
                style = Stroke(5.dp.toPx())
            )
        },
        color = Color.LightGray,
        strokeWidth = 5.dp
    )
}