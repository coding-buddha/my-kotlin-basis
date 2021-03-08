package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import edu.pasudo123.kotlin.mykotlin.samplehttplib.model.TaylorQuote

class SimpleGetClient {

    var client: SimpleGetApi = RetrofitInstance.api

    suspend fun getTaylor(): TaylorQuote {
        return client.getQuote()
    }
}