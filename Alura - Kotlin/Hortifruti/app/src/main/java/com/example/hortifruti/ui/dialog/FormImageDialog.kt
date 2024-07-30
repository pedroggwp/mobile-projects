package com.example.hortifruti.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import com.example.hortifruti.databinding.FormImageBinding
import com.example.hortifruti.extensions.tryToLoadImage

class FormImageDialog(
    val context: Context
) {

    fun show(imageView: ImageView) {
        val binding = FormImageBinding.inflate(LayoutInflater.from(context))

        // Carregar imagem passada na url
        binding.formImageLoadButton.setOnClickListener {
            val url = binding.formImageUrl.text.toString()
            binding.formImageImageview.tryToLoadImage(url)
        }

        // Dialog
        AlertDialog.Builder(context)
            .setView(binding.root)
            .setPositiveButton("Confirmar") { _, _ ->
                val url = binding.formImageUrl.text.toString()
                Log.i("FormularioImagemDialog", "mostra: $url")
                imageView.tryToLoadImage(url)
            }
            .setNegativeButton("Cancelar") {_, _ ->

            }
            .show()
    }
}