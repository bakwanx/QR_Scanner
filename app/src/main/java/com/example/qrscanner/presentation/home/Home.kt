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
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.qrscanner.R
import com.example.qrscanner.presentation.TransactionViewModel
import com.example.qrscanner.presentation.history.HistoryActivity
import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.presentation.pay.model.PayModel
import com.example.qrscanner.presentation.scan.ScanActivity
import com.example.qrscanner.util.formatDate
import com.example.qrscanner.util.toCurrencyFormat

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
    ) {
        Header(balance.toString())
        Action(context)
        if(historyModels != null){
            LazyColumn(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp),
            ){
                items(historyModels!!){
                    PromoCard(promoModel = it)
                }
            }
        } else {

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
fun PromoCard(promoModel: PromoModel) {

    Box {
        Card(
            colors = CardDefaults.cardColors(containerColor = Color.White),
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                AsyncImage(
                    model = promoModel.img.url,
                    contentDescription = "caption",
                    modifier = Modifier
                        .size(50.dp)
                        .fillMaxWidth()
                )
                Text(
                    text = promoModel.nama,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = promoModel.desc,
                    fontWeight = FontWeight.Light
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
fun LoadingAnimation1(
    circleColor: Color = Color.Magenta,
    animationDelay: Int = 1000
) {

    // circle's scale state
    var circleScale by remember {
        mutableStateOf(0f)
    }

    // animation
    val circleScaleAnimate = animateFloatAsState(
        targetValue = circleScale,
        animationSpec = infiniteRepeatable(
            animation = tween(
                durationMillis = animationDelay
            )
        )
    )

    // This is called when the app is launched
    LaunchedEffect(Unit) {
        circleScale = 1f
    }

    // animating circle
    Box(
        modifier = Modifier
            .size(size = 64.dp)
            .scale(scale = circleScaleAnimate.value)
            .border(
                width = 4.dp,
                color = circleColor.copy(alpha = 1 - circleScaleAnimate.value),
                shape = CircleShape
            )
    ) {

    }
}

