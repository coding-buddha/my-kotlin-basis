package com.example.springbootconcurrencybasis.client.concert

import com.example.springbootconcurrencybasis.domain.conert.model.Concert
import okhttp3.OkHttpClient
import org.springframework.stereotype.Component
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.POST

class ConcertClientMaker(
    private val host: String,
    private val useDummy: Boolean
) {

    interface ConcertClient {
        fun createConcert(): Call<Concert>
    }

    interface ConcertRealClient: ConcertClient {
        @POST("concerts")
        override fun createConcert(

        ): Call<Concert>
    }

    interface ConcertDummyClient: ConcertClient {
        override fun createConcert(): Call<Concert> {
            TODO("")
        }
    }

    fun createClient(): ConcertClient {
        val httpClient = OkHttpClient.Builder()
            .addInterceptor { chain ->
                val builder = chain.request().newBuilder()
                    .header("Accept", "application/json")
                val request = builder.build()
                chain.proceed(request)
            }.build()

        val retrofit = Retrofit.Builder()
            .baseUrl(host)
            .addConverterFactory(JacksonConverterFactory.create())
            .callFactory(httpClient).build()

        if(this.useDummy) {
            return retrofit.create(ConcertDummyClient::class.java)
        }

        return retrofit.create(ConcertRealClient::class.java)
    }
}