package com.example.hortifruti.extensions

import java.math.BigDecimal
import java.text.NumberFormat
import java.util.Locale

// função de extensão para reutilizar o código de formatação em diferentes pontos do código

fun BigDecimal.formatToBrasilianCoin(): String {
    val formatador: NumberFormat = NumberFormat
        .getCurrencyInstance(Locale("pt", "br"))
    return formatador.format(this)
}