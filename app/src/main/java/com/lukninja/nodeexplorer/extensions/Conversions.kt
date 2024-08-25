package com.lukninja.nodeexplorer.extensions

import android.util.Log
import java.math.BigDecimal
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneId
import java.time.format.DateTimeFormatter

fun Long.toDate(pattern: String) : String {
    val dateHour = LocalDateTime.ofInstant(Instant.ofEpochSecond(this), ZoneId.systemDefault())

    return dateHour.format(DateTimeFormatter.ofPattern(pattern))
}

fun Long.toDateUpdate() : String {
    val dateHourNow = LocalDateTime.now()
    val dateHour = LocalDateTime.ofInstant(Instant.ofEpochSecond(this), ZoneId.systemDefault())
    val dateHourOptimized = if (dateHourNow.dayOfMonth - dateHour.dayOfMonth >= 1 || dateHourNow.hour < dateHour.hour) {
        dateHour.format(DateTimeFormatter.ofPattern("dd/MM/yy HH:mm"))
    } else {
        dateHour.format(DateTimeFormatter.ofPattern("HH:mm"))
    }

    return dateHourOptimized
}

fun Long.toBitcoin() : String {
    var bitcoinFormated = ""
    try {
        bitcoinFormated = BigDecimal(this).divide(BigDecimal(100_000_000)).toString()
    } catch (e: Exception){
        Log.e("Converter", "Exception: $e")
        Log.e("Converter", "Sats: $this")
        bitcoinFormated = this.toString()
    }

    return bitcoinFormated
}