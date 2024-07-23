package com.example.hortifruti.ui.recyclerview.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hortifruti.databinding.ItemProductBinding
import com.example.hortifruti.model.Product

class ProductAdapter(
    private val context: Context,
    products: List<Product>
) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    private val products = products.toMutableList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val binding = ItemProductBinding.inflate(
            LayoutInflater.from(context),
            parent,
            false
        )

        return ProductViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product: Product = products[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int = products.size

    fun updateList(products: List<Product>) {
        this.products.clear()
        this.products.addAll(products)
        notifyDataSetChanged()
    }

    class ProductViewHolder(binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name = binding.itemProductName
        private val description = binding.itemProductDescription
        private val value = binding.itemProductValue

        fun bind(product: Product) {
            name.text = product.name
            description.text = product.description
            value.text = product.value.toPlainString()
        }
    }
}
