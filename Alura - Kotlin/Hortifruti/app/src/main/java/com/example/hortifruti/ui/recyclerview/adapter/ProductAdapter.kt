package com.example.hortifruti.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hortifruti.databinding.ItemProductBinding
import com.example.hortifruti.extensions.tryToLoadImage
import com.example.hortifruti.model.Product
import java.text.NumberFormat
import java.util.Locale

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

    class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        private val name = binding.itemProductName
        private val description = binding.itemProductDescription
        private val value = binding.itemProductValue
        private val formatter: NumberFormat = NumberFormat.getCurrencyInstance(Locale("pt", "br"))

        fun bind(product: Product) {
            name.text = product.name
            description.text = product.description
            Log.d("formatter", formatter.toString())
            val valueInCoin: String = formatter.format(product.value)
            value.text = valueInCoin

            val visibility = if (product.imageUrl != null) {
                View.VISIBLE
            } else {
                View.GONE
            }

            binding.imageView.visibility = visibility

            binding.imageView.tryToLoadImage(product.imageUrl)
        }
    }
}
