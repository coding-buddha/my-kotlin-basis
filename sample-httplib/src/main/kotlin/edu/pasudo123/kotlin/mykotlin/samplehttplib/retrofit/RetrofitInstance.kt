package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import edu.pasudo123.kotlin.mykotlin.samplehttplib.public.PublicApis
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory

object RetrofitInstance {

    val api: SimpleGetApi by lazy {
        val retrofit = Retrofit.Builder()
            .baseUrl(PublicApis.TAYLOR_BASE_URL)
            .addConverterFactory(JacksonConverterFactory.create())
            .build()

        retrofit.create(SimpleGetApi::class.java)
    }
}