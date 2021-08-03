package com.example.fakestore.network

import retrofit2.Call
import retrofit2.http.*

interface Api {
    @GET("products")
    fun getProducts(): Call<ArrayList<ProductsResponse>>

    @GET("products")
    fun getSortProducts(
        @Query("order") order: String,
        @Query("sort") sort: String
    ): Call<ArrayList<ProductsResponse>>

    @GET
    fun getProductsSingle(@Url url: String): Call<ProductsResponse>

    @GET("/products/{id}")
    fun getProductsSingle(@Path("id") id: Int): Call<ProductsResponse>

    @GET
    fun getProductsLimit(@Url url: String): Call<ArrayList<ProductsResponse>>


}