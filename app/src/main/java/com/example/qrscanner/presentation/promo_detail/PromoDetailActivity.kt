package com.example.qrscanner.presentation.promo_detail

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.qrscanner.presentation.TransactionViewModel
import com.example.qrscanner.presentation.home.Home
import com.example.qrscanner.presentation.home.PromoViewModel
import com.example.qrscanner.presentation.home.model.PromoModel
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.scanText
import dagger.hilt.android.AndroidEntryPoint
import retrofit2.http.Body

@AndroidEntryPoint
class PromoDetailActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val promoModel =  intent.getSerializableExtra(KEY_PROMO_DETAIL) as PromoModel
        setContent {
            QRScannerTheme {
                Surface(color = Color.White) {
                    Body(promoModel = promoModel)
                }
            }
        }
    }

    companion object{
        val KEY_PROMO_DETAIL = "key promo detail"
    }
}

@Composable
fun Body(promoModel: PromoModel){
    Column(
    ) {
        AsyncImage(
            model = promoModel.img.url,
            contentDescription = "",
            contentScale = ContentScale.Fit,
            modifier = Modifier.fillMaxWidth()
        )
        Text(
            text = promoModel.nama,
            fontWeight = FontWeight.Bold,
            fontSize = 16.sp,
            modifier = Modifier.padding(
                top = 12.dp,
                start = 24.dp,
                end = 24.dp
            )
        )
        Text(
            text = promoModel.desc,
            fontWeight = FontWeight.Normal,
            modifier = Modifier.padding(
                top = 8.dp,
                start = 24.dp,
                end = 24.dp
            )
        )
    }
}