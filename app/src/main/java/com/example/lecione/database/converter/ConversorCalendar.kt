package com.example.lecione.database.converter

import androidx.room.TypeConverter
import java.util.*

class ConversorCalendar {

    @TypeConverter
    fun toLong(calendar: Calendar?): Long? {
        return calendar?.timeInMillis
    }

    @TypeConverter
    fun toCalendar(long: Long?): Calendar {
        val calendar = Calendar.getInstance()
        if (long != null) {
            calendar.timeInMillis = long
        }
        return calendar
    }
}
