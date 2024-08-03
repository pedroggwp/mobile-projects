package com.example.hortifruti.ui.recyclerview.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.hortifruti.databinding.ItemProductBinding
import com.example.hortifruti.extensions.formatToBrasilianCoin
import com.example.hortifruti.extensions.tryToLoadImage
import com.example.hortifruti.model.Product
import java.text.NumberFormat
import java.util.Locale

class ProductAdapter(
    private val context: Context,
    products: List<Product>,

    // declaração da função para o listener do adapter
    var whenClickedOnItem: (product: Product) -> Unit = {}
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

    // utilização do inner na classe interna para acessar membros da classe superior, nesse caso, a utilização da variável whenClickedOnItem
    inner class ProductViewHolder(private val binding: ItemProductBinding) : RecyclerView.ViewHolder(binding.root) {
        // Considerando que o ViewHolder modifica de valor com base na posição, é necessário o uso de properties mutáveis, para evitar nullables
        // utilizamos o lateinit, properties que podem ser inicializar depois

        private lateinit var product: Product

        init {
            // implementação do listener do adapter
            itemView.setOnClickListener {
                // verificação da existência de valores em property lateinit
                if (::product.isInitialized) {
                    whenClickedOnItem(product)
                }
            }
        }

        fun bind(product: Product) {
            this.product = product

            val name = binding.itemProductName
            name.text = product.name

            val description = binding.itemProductDescription
            description.text = product.description

            val value = binding.itemProductValue
            val valueInCoin: String = product.value.formatToBrasilianCoin()
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
