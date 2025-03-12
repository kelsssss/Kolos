package com.example.kolos.network

import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    @GET("coins/markets?x_cg_demo_api_key=CG-JgzNetfueE5S2carhKRYksD8")
    suspend fun getCoinsData(@Query("vs_currency") currency: String = "usd"): List<CoinsData>
}