package com.example.qrscanner.presentation.activity.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable



class DonutChartModel(
    val type: String,
    val data: List<DataDonutChartModel>
): java.io.Serializable



class DataDonutChartModel(
    val label: String,
    val percentage: String,
    val data: List<LabelDetailModel>
) : java.io.Serializable


class LabelDetailModel(

    val trx_date: String,
    val nominal: Long
) : java.io.Serializable

