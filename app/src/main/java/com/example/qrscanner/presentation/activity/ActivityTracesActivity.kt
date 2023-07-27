package com.example.qrscanner.presentation.activity

import android.graphics.drawable.Animatable
import android.os.Bundle
import android.util.Log
import android.util.Size
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.animation.Animatable
import androidx.compose.animation.core.tween
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.min
import com.example.qrscanner.presentation.TransactionViewModel
import com.example.qrscanner.presentation.activity.model.DonutChartModel
import com.example.qrscanner.presentation.history.HistoryCard
import com.example.qrscanner.ui.theme.QRScannerTheme
import com.example.qrscanner.util.getJsonDataFromAsset
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import dagger.hilt.android.AndroidEntryPoint
import java.lang.Math.atan2
import kotlin.math.roundToInt


@AndroidEntryPoint
class ActivityTracesActivity : ComponentActivity()  {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val jsonFileString = getJsonDataFromAsset(applicationContext, "donutChart.json")
        Log.i("data", jsonFileString.toString())

        val gson = Gson()
        val listPersonType = object : TypeToken<DonutChartModel>() {}.type

        var persons: List<DonutChartModel> = gson.fromJson(jsonFileString, listPersonType)
        persons.forEachIndexed { idx, donutChartModel -> Log.i("data", "> Item $idx:\n$donutChartModel") }
        setContent {
            QRScannerTheme {

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(24.dp),
                ){

                }
            }
        }
    }
}
