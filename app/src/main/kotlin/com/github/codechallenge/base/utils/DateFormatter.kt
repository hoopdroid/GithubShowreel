package com.github.codechallenge.base.utils

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import org.joda.time.DateTime
import org.joda.time.format.DateTimeFormat
import java.lang.Exception
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

internal class DateFormatter @Inject constructor(
    @ApplicationContext private val appContext: Context,
    private val calendar: Calendar
) {
    companion object {
        private const val DATE_FORMAT = "MMM dd, yyyy"
    }

    fun format(dateTime: DateTime?): String {
        return try {
            val formatter = DateTimeFormat.forPattern(DATE_FORMAT)
            formatter.print(dateTime)
        } catch (exception: Exception) {
            ""
        }
    }

    fun getMonthName(monthOfYear: Int): String {
        val formatter = SimpleDateFormat("LLL", appContext.resources.configuration.locale)
        calendar.set(Calendar.MONTH, monthOfYear)
        return try {
            formatter.format(calendar.time)
        } catch (exception: Exception) {
            ""
        }
    }
}
