package com.test.cars.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtil {

    private const val HOUR_FORMAT_12 = " hh:mm aa"
    private const val HOUR_FORMAT_24 = " HH:mm "
    private const val DATE_TIME_FORMAT_API = "dd.MM.yyyy HH:mm"
    private const val DATE_SHOW_FORMAT_WITH_YEAR = "dd MMMM yyyy,"
    private const val DATE_SHOW_FORMAT_WITHOUT_YEAR = "dd MMMM,"

    /**
     * convert date string to calendar object
     * @param dateTime
     * @param format
     */
    private fun toCalendar(dateTime: String?, format: String): Calendar? {
        if (dateTime == null) return null
        val simpleDateFormat = SimpleDateFormat(format,Locale.getDefault(Locale.Category.FORMAT))
        simpleDateFormat.timeZone = TimeZone.getTimeZone("UTC")
        try {
            val date = simpleDateFormat.parse(dateTime)
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = date!!.time
            return calendar
        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return null
    }

    /**
     * Get full date from the date given
     * @param dateTime
     */
    fun getFullDateDisplay(dateTime: String?): String {
        val calendar = toCalendarDefault(dateTime)
        var returnDate = String()
        if (calendar != null) {
            val hourFormat = getHourFormat(is24HourFormat)
            val dateString = toString(calendar, DATE_SHOW_FORMAT_WITH_YEAR + hourFormat)
            val yearFromDateTime = getYearFromDateDisplay(dateTime)
            val currentYear = getCurrentYear()
            returnDate = if (yearFromDateTime == currentYear) {
                toString(calendar, DATE_SHOW_FORMAT_WITHOUT_YEAR + hourFormat)
            } else {
                dateString
            }
        }
        return returnDate
    }

    /**
     * convert to String date
     * @param calendar
     * @param format
     */
    private fun toString(calendar: Calendar?, format: String): String {
        if (calendar == null) return String()
        val simpleDateFormat = SimpleDateFormat(format,Locale.getDefault(Locale.Category.FORMAT))
        return simpleDateFormat.format(calendar.time)
    }

    /**
     * get Hour format
     */
    private fun getHourFormat(timeFormat: Boolean) =
        when (timeFormat) {
            true -> HOUR_FORMAT_24
            false -> HOUR_FORMAT_12
        }

    /**
     * Get year from the date given
     * @param dateTime
     */
    private fun getYearFromDateDisplay(dateTime: String?): Int {
        val calendar = toCalendarDefault(dateTime)
        return calendar?.get(Calendar.YEAR) ?: getCurrentYear()
    }

    /**
     * convert to default calendar format
     * @param dateTime
     */
    private fun toCalendarDefault(dateTime: String?) = toCalendar(dateTime, DATE_TIME_FORMAT_API)

    /**
     * get current year
     */
    private fun getCurrentYear() = Calendar.getInstance().get(Calendar.YEAR)

    /*
     * is 24 Hour Format
     */
    var is24HourFormat: Boolean = false
}
