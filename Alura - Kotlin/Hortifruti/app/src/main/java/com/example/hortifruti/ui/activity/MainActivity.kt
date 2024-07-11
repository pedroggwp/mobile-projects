package com.example.hortifruti.ui.activity

import android.app.Activity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.hortifruti.R
import com.example.hortifruti.model.Product
import com.example.hortifruti.ui.recyclerview.adapter.ProductAdapter
import java.math.BigDecimal

class MainActivity : Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val recyclerView = findViewById<RecyclerView>(R.id.products_recycler_view)

        recyclerView.adapter = ProductAdapter(this, listOf(
            Product(
                "teste 1 ",
                "teste desc 1",
                BigDecimal("99.22")
            ),
            Product(
                "teste 2",
                "teste desc 2",
                BigDecimal("22")
            ),
            Product(
                "teste 3",
                "teste desc 3",
                BigDecimal("22")
            ),
            Product(
                "teste 4",
                "teste desc 4",
                BigDecimal("22")
            ),
            Product(
                "teste 5",
                "teste desc 5",
                BigDecimal("22")
            ),
            Product(
                "teste 6",
                "teste desc 6",
                BigDecimal("22")
            ),
            Product(
                "teste 7",
                "teste desc 7",
                BigDecimal("22")
            ),
            Product(
                "teste 8",
                "teste desc 8",
                BigDecimal("22")
            )
        ))

        recyclerView.layoutManager = LinearLayoutManager(this)
    }
}