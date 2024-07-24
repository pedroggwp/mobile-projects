package com.example.hortifruti.dao

import com.example.hortifruti.model.Product
import java.math.BigDecimal

class ProductsDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll() : List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>(
            Product(name = "Salada de Frutas", description = "Laranja, maçãs e uvas", value = BigDecimal("19.83"))
        )
    }
}