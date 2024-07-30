package com.example.hortifruti.extensions

import android.widget.ImageView
import coil.load
import com.example.hortifruti.R

fun ImageView.tryToLoadImage(url: String? = null) {
    load(url) {
        fallback(R.drawable.erro)
        error(R.drawable.erro)
        placeholder(R.drawable.placeholder)
    }
}