package com.gbl.culturalactivities.ui.tools

import android.icu.text.DateFormat
import java.util.Calendar
import java.util.Locale

fun Long.dateToStringWithLongFormat(): String {
    val calendar = Calendar.getInstance()
    calendar.timeInMillis = this
    val dateFormatter = DateFormat.getDateInstance(DateFormat.LONG, Locale.getDefault())
    return dateFormatter.format(calendar.time)
}