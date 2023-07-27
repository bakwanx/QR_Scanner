package com.example.qrscanner.presentation.activity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
class DonutChartModel(
    val type: String,
    val data: List<DataDonutChartModel>
)


@Serializable
class DataDonutChartModel(
    val label: String,
    val percentage: String,
    val data: List<LabelDetailModel>
)

@Serializable
class LabelDetailModel(
    @SerialName("trx_date")
    val trxDate: String,

    val nominal: Long
)

