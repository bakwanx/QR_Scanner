package com.example.qrscanner.presentation.activity.model

import kotlinx.serialization.Serializable

@Serializable
data class LineChartModel (
    val type: String,
    val data: DataLineChart
)

@Serializable
data class DataLineChart (
    val month: List<Int>
)
