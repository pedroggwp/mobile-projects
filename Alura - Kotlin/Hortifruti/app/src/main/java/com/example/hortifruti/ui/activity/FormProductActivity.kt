package com.example.hortifruti.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.hortifruti.R
import com.example.hortifruti.dao.ProductsDAO
import com.example.hortifruti.databinding.ActivityFormProductBinding
import com.example.hortifruti.model.Product
import java.math.BigDecimal

class FormProductActivity : AppCompatActivity(R.layout.activity_form_product) {

    private val dao = ProductsDAO()

    private val binding by lazy {
        ActivityFormProductBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val button = binding.buttonSave
        button.setOnClickListener {
            val createdProduct = createProduct()
            dao.add(createdProduct)

            finish() // finaliza a activity após o usuário clicar no botão SAVE
        }

    }

    private fun createProduct(): Product {
        val fieldName = binding.formName
        val name: String = fieldName.text.toString()

        val fieldDescription = binding.formDescription
        val description: String = fieldDescription.text.toString()

        val fieldValue = binding.formValue
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