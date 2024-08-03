package com.example.mobile_pedrohenrique_2.extension

import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun String.formatIsoDateToDateTimeString(pattern: String = "dd/MM/yyyy HH:mm:ss"): String {
    val instant = Instant.parse(this)
    val formatter = DateTimeFormatter.ofPattern(pattern).withZone(ZoneId.systemDefault())
    return formatter.format(instant)
}