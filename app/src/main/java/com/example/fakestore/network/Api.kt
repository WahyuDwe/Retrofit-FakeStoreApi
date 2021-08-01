package com.example.fakestore.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface Api {
    @GET("products")
    fun getProducts(): Call<ArrayList<ProductsResponse>>
}