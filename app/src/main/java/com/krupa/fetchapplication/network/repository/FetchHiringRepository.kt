package com.krupa.fetchapplication.network.repository

import com.krupa.fetchapplication.network.ApiService
import com.krupa.fetchapplication.network.response.HiringResponse
import javax.inject.Inject

class FetchHiringRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun fetchHiring() :List<HiringResponse> {
        return apiService.fetchHiringResponse().filterNot { it.name.isNullOrBlank() }
    }
}