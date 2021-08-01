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

        showProducts()
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
}