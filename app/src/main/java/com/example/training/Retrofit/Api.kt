package com.example.training.Retrofit

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface ApiService {
    @POST("chat")
    suspend fun postRequest(@Body requestBody: RequestBodyModel): ResponseModel
}

object RetrofitClient {
    private const val BASE_URL = "https://support.matlubapps.com/"

    val instance: ApiService by lazy {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(ApiService::class.java)
    }
}