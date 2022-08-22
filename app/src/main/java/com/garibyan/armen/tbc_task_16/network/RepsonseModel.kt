package com.garibyan.armen.tbc_task_16.network

import com.squareup.moshi.Json

data class ResponseModel(
    val page: Int,
    @Json(name = "per_page")
    val perPage: Int,
    val total: Int,
    @Json(name = "total_pages")
    val totalPages: Int,
    val data: List<Person>
){
    data class Person(
        val id: Int,
        val email: String,
        @Json(name = "first_name")
        val firstname: String,
        @Json(name = "last_name")
        val lastname: String,
        val avatar: String
    )
}

