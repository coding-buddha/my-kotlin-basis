package edu.pasudo123.kotlin.mykotlin.samplehttplib.retrofit

import edu.pasudo123.kotlin.mykotlin.samplehttplib.model.TaylorQuote
import edu.pasudo123.kotlin.mykotlin.samplehttplib.public.PublicApis
import io.reactivex.Observable
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.Test
import retrofit2.Call

/**
 * https://kotlintesting.com/testing-retrofit-calls/
 */
@DisplayName("SimpleGetApi 는")
class RetrofitClientTest {

    @Test
    @DisplayName("")
    fun getApiTest() {

        // given
        val client = RetrofitInstance.getClient(PublicApis.TAYLOR_BASE_URL)

        // when
        val response: Observable<TaylorQuote> = client.getQuote()

        // then
        println(response.blockingSingle())
   }
}