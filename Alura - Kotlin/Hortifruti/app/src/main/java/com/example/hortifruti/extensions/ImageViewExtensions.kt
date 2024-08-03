package com.example.hortifruti.extensions

import android.util.Log
import android.widget.ImageView
import coil.load
import com.example.hortifruti.R

fun ImageView.tryToLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.imagem_padrao)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}