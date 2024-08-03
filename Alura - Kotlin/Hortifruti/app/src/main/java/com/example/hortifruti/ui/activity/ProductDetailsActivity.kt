package com.example.hortifruti.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hortifruti.databinding.ActivityProductDetailsBinding
import com.example.hortifruti.extensions.formatToBrasilianCoin
import com.example.hortifruti.extensions.tryToLoadImage
import com.example.hortifruti.model.Product

class ProductDetailsActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityProductDetailsBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        tryLoadProduct()
    }

    private fun tryLoadProduct() {
        // tentativa de buscar o produto se ele existir, caso contrário, finalizar a Activity

        intent.getParcelableExtra<Product>(KEY_PRODUCT)?.let {
            loadedProduct ->
                fillFields(loadedProduct)
        }
    }

    private fun fillFields(loadedProduct: Product) {
        with(binding) {
            activityDetailsProductImage.tryToLoadImage(loadedProduct.imageUrl)
            activityDetailsProductName.text = loadedProduct.name
            activityDetailsProductDescription.text = loadedProduct.description
            activityDetailsProductValue.text = loadedProduct.value.formatToBrasilianCoin()
        }
    }
}