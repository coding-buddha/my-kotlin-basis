package com.example.springbootconcurrencybasis.client.ticket

import com.example.springbootconcurrencybasis.domain.ticket.model.Ticket
import okhttp3.OkHttpClient
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.jackson.JacksonConverterFactory
import retrofit2.http.POST
import retrofit2.http.Path

class TicketClientMaker(
    private val host: String
) {

    interface TicketClient {
        @POST("tickets/concert/{id}")
        fun createTicket(
            @Path("id") id: Long
        ): Call<Ticket>
    }

    fun createClient(): TicketClient {
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

        return retrofit.create(TicketClient::class.java)
    }
}