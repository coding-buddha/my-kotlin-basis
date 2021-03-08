package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitInstance {

    fun getClient(baseUrl: String): RetrofitClient {
        return  Retrofit.Builder()
            .baseUrl(baseUrl)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addConverterFactory(JacksonConverterFactory.create())
            .build()
            .create(RetrofitClient::class.java)
    }
}