package com.example.hortifruti.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.hortifruti.R
import com.example.hortifruti.dao.ProductsDAO
import com.example.hortifruti.ui.recyclerview.adapter.ProductAdapter
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListProductActivity : AppCompatActivity(R.layout.activity_list_product) {

    private val dao = ProductsDAO()
    private val adapter = ProductAdapter(this, dao.findAll())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        configRecyclerView()
        configFloatingActionButton()
    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(dao.findAll())
    }

    private fun configFloatingActionButton() {
        val fab = findViewById<FloatingActionButton>(R.id.list_product_floating_action_button)
        fab.setOnClickListener {
            val intent = Intent(this, FormProductActivity::class.java)
            startActivity(intent)
        }
    }

    private fun configRecyclerView() {
        val recyclerView = findViewById<RecyclerView>(R.id.list_product_recyclerview)
        recyclerView.adapter = adapter
    }
}