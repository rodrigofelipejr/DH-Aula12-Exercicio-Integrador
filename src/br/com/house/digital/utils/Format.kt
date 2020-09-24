package br.com.house.digital.utils

import java.text.NumberFormat
import java.util.*

fun kotlin.Double.localCurrency(): String {
    return NumberFormat.getCurrencyInstance(Locale("pt", "BR")).format(this)
}