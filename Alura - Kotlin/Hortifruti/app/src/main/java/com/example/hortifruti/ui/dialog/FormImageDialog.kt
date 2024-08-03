package com.example.hortifruti.ui.dialog

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import com.example.hortifruti.databinding.FormImageBinding
import com.example.hortifruti.extensions.tryToLoadImage

class FormImageDialog(
    private val context: Context
) {

    fun show(
        urlImageStandart: String? = null,
        whenImageLoaded: (urlImage: String) -> Unit
    ) {

        // binding
        FormImageBinding.inflate(LayoutInflater.from(context)).apply {
            urlImageStandart?.let {
                formImageImageview.tryToLoadImage(it)
                formImageUrl.setText(it)
            }

            // Carregar imagem passada na url
            formImageLoadButton.setOnClickListener {
                val url = formImageUrl.text.toString()
                formImageImageview.tryToLoadImage(url)
            }

            // Dialog
            AlertDialog.Builder(context)
                .setView(root)
                .setPositiveButton("Confirmar") { _, _ ->
                    val url = formImageUrl.text.toString()
                    Log.i("FormularioImagemDialog", "mostra: $url")
                    whenImageLoaded(url)
                }
                .setNegativeButton("Cancelar") {_, _ ->

                }
                .show()
        }
    }
}