package com.example.fakestore.network

data class ProductsResponse(
    val id: Int,
    val title: String,
    val price: Double,
    val category: String,
    val description: String,
    val image: String

)
