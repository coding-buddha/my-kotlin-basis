package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import edu.pasudo123.kotlin.mykotlin.samplehttplib.model.TaylorQuote
import retrofit2.http.GET

interface SimpleGetApi {

    @GET
    suspend fun getQuote(): TaylorQuote
}