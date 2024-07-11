package com.example.hortifruti.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.hortifruti.R
import com.example.hortifruti.model.Product

class ProductAdapter(
    private val context: Context,
    private val products: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(context).inflate(
            R.layout.item_product,
            parent,
            false
        )

        return ProductViewHolder(view)
    }


    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    class ProductViewHolder(view: View) : RecyclerView.ViewHolder(view) {

        fun bind(product: Product) {
            val name = itemView.findViewById<TextView>(R.id.fruit_name)
            name.text = product.name

            val description = itemView.findViewById<TextView>(R.id.fruit_description)
            description.text = product.description

            val value = itemView.findViewById<TextView>(R.id.fruit_value)
            value.text = product.value.toPlainString()
        }

    }
}
