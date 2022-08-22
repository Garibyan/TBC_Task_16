package com.garibyan.armen.tbc_task_16.network

import com.garibyan.armen.tbc_task_16.utils.Constants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET(Constants.END_POINT)
    suspend fun getUsers(@Query("page") page: Int): ResponseModel
}