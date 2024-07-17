package com.example.hortifruti.dao

import com.example.hortifruti.model.Product

class ProductsDAO {

    fun add(product: Product) {
        products.add(product)
    }

    fun findAll() : List<Product> {
        return products.toList()
    }

    companion object {
        private val products = mutableListOf<Product>()
    }
}