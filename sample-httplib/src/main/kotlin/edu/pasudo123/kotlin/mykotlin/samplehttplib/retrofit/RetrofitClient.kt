package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import edu.pasudo123.kotlin.mykotlin.samplehttplib.model.TaylorQuote
import io.reactivex.Observable
import retrofit2.http.GET

interface RetrofitClient {

    @GET(".")
    fun getQuote(): Observable<TaylorQuote>
}