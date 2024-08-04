package com.example.hortifruti.model

import android.os.Parcelable
import androidx.room.Entity
import kotlinx.parcelize.Parcelize
import java.math.BigDecimal

@Entity
@Parcelize
data class Product(
    val name: String,
    val description: String,
    val value: BigDecimal,
    val imageUrl: String? = null
) : Parcelable
