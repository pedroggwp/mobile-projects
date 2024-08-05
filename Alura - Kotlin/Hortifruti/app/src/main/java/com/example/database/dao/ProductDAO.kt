package com.example.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.hortifruti.model.Product

@Dao
interface ProductDAO {

    @Query("SELECT * FROM Product")
    fun getAllProducts(): List<Product>

    @Insert
    fun saveProduct(vararg product: Product)
}