package b.varchenko.data.network

import b.varchenko.data.network.dto.RequestReceiptResponseDto
import retrofit2.Call
import retrofit2.http.GET

internal interface ReceiptApi {

    @GET("/")
    fun getPurchase(): Call<RequestReceiptResponseDto>
}