package com.example.hortifruti.ui.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hortifruti.R
import com.example.hortifruti.dao.ProductsDAO
import com.example.hortifruti.databinding.ActivityListProductBinding
import com.example.hortifruti.ui.recyclerview.adapter.ProductAdapter

class ListProductActivity : AppCompatActivity(R.layout.activity_list_product) {

    private val dao = ProductsDAO()
    private val adapter = ProductAdapter(this, dao.findAll())

    private val binding by lazy {
        ActivityListProductBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        configRecyclerView(binding)
        configFloatingActionButton(binding)
    }

    override fun onResume() {
        super.onResume()
        adapter.updateList(dao.findAll())
    }

    private fun configRecyclerView(binding: ActivityListProductBinding) {
        val recyclerView = binding.listProductRecyclerview
        recyclerView.adapter = adapter
    }

    private fun configFloatingActionButton(binding: ActivityListProductBinding) {
        val fab = binding.listProductExtendedFloatingActionButton
        fab.setOnClickListener {
            val intent = Intent(this, FormProductActivity::class.java)
            startActivity(intent)
        }
    }
}