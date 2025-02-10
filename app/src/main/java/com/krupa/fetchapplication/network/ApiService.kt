package com.krupa.fetchapplication.network

import com.krupa.fetchapplication.network.response.HiringResponse
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun fetchHiringResponse(): List<HiringResponse>
}