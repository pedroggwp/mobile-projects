package com.example.aulaactivityfragment

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import java.io.Serializable

//data class Filme(
//    val nome: String,
//    val descricao: String,
//    val avaliacoes: Double,
//    val diretor: String,
//    val distribuidor: String
//) : Serializable

@Parcelize
data class Filme(
    val nome: String,
    val descricao: String,
    val avaliacoes: Double,
    val diretor: String,
    val distribuidor: String
) : Parcelable
