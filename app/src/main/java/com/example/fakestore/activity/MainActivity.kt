package com.example.fakestore.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.fakestore.R
import com.example.fakestore.adapter.ProductsAdapter
import com.example.fakestore.network.ProductsResponse
import com.example.fakestore.network.RetrofitStore
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private var list = ArrayList<ProductsResponse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        showProducts()
//        showProductsSingle()
//        showProductsLimit()
        showSortProducts()
    }

    private fun showSortProducts() {
        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = LinearLayoutManager(this)
        RetrofitStore.instance.getSortProducts( "id","desc").enqueue(object : Callback<ArrayList<ProductsResponse>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsResponse>>,
                response: Response<ArrayList<ProductsResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = ProductsAdapter(list)
                rvProducts.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ProductsResponse>>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showProducts() {
        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = LinearLayoutManager(this)

        RetrofitStore.instance.getProducts().enqueue(object : Callback<ArrayList<ProductsResponse>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsResponse>>,
                response: Response<ArrayList<ProductsResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = ProductsAdapter(list)
                rvProducts.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ProductsResponse>>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }

    private fun showProductsSingle() {
        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = LinearLayoutManager(this)
        RetrofitStore.instance.getProductsSingle(1).enqueue(object : Callback<ProductsResponse>{
            override fun onResponse(
                call: Call<ProductsResponse>,
                response: Response<ProductsResponse>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(listOf(it)) }
                val adapter = ProductsAdapter(list)
                rvProducts.adapter = adapter
            }

            override fun onFailure(call: Call<ProductsResponse>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })

    }

    private fun showProductsLimit() {
        rvProducts.setHasFixedSize(true)
        rvProducts.layoutManager = LinearLayoutManager(this)

        RetrofitStore.instance.getProductsLimit("/products?limit=5").enqueue(object : Callback<ArrayList<ProductsResponse>>{
            override fun onResponse(
                call: Call<ArrayList<ProductsResponse>>,
                response: Response<ArrayList<ProductsResponse>>
            ) {
                val responseCode = response.code().toString()
                tvResponseCode.text = responseCode
                response.body()?.let { list.addAll(it) }
                val adapter = ProductsAdapter(list)
                rvProducts.adapter = adapter
            }

            override fun onFailure(call: Call<ArrayList<ProductsResponse>>, t: Throwable) {
                tvResponseCode.text = t.message
            }

        })
    }
}