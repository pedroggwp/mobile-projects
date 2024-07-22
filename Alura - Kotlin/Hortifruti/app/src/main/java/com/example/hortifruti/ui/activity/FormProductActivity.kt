package com.example.hortifruti.ui.activity

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.hortifruti.R
import com.example.hortifruti.dao.ProductsDAO
import com.example.hortifruti.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {

    private val dao = ProductsDAO()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val button = findViewById<Button>(R.id.button_save)
        button.setOnClickListener {
            val createdProduct = createProduct()
            dao.add(createdProduct)

            finish() // finaliza a activity após o usuário clicar no botão SAVE
        }

    }

    private fun createProduct(): Product {
        val fieldName = findViewById<EditText>(R.id.form_name)
        val name: String = fieldName.text.toString()

        val fieldDescription = findViewById<EditText>(R.id.form_description)
        val description: String = fieldDescription.text.toString()

        val fieldValue = findViewById<EditText>(R.id.form_value)
        val valueStr: String = fieldValue.text.toString()

        val value = if (valueStr.isBlank()) {
            BigDecimal.ZERO
        } else {
            BigDecimal(valueStr)
        }

        return Product(
            name = name,
            description = description,
            value = value
        )

    }
}