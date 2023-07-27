package com.example.qrscanner.rest_api

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ApiClient {
    companion object {
        fun getService(): ApiService {
            val client = OkHttpClient.Builder()
                .addNetworkInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build()
            val build = Retrofit.Builder()
                .client(client)
                .baseUrl("https://content.digi46.id/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()
            return build.create(ApiService::class.java)
        }
    }
}