package com.example.springbootconcurrencybasis.client.concert

import com.example.springbootconcurrencybasis.client.concert.resource.ConcertResource
import retrofit2.Call

class ConcertClient {
    private var concertRealClient: ConcertRealClient? = null
    private var concertDummyClient: ConcertDummyClient? = null

    fun setRealClient(concertRealClient: ConcertRealClient) {
        this.concertRealClient = concertRealClient
    }

    fun setDummyClient(concertDummyClient: ConcertDummyClient) {
        this.concertDummyClient = concertDummyClient
    }

    fun createConcert(resource: ConcertResource.CreateRequest): Call<ConcertResource.Reply> {
        concertRealClient?.let {
            return it.createConcert(resource)
        }

        return concertDummyClient!!.createConcert(resource)
    }
}