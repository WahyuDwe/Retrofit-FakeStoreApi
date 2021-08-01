package com.example.fakestore.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.fakestore.R
import com.example.fakestore.network.ProductsResponse
import kotlinx.android.synthetic.main.item_posts.view.*

class ProductsAdapter(private val list: ArrayList<ProductsResponse>) :
    RecyclerView.Adapter<ProductsAdapter.ProductsViewHolder>() {
    inner class ProductsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(productsResponse: ProductsResponse) {
            with(itemView) {
                Glide.with(itemView).load(productsResponse.image).into(ivProducts)
                val text = "id: ${productsResponse.id}\n" +
                        "title: ${productsResponse.title}\n" +
                        "price: ${productsResponse.price}\n" +
                        "category: ${productsResponse.category}\n" +
                        "description: ${productsResponse.description}"
                tvProducts.text = text
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_posts, parent, false)
        return ProductsViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductsViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}