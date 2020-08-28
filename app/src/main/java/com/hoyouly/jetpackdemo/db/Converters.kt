package com.hoyouly.jetpackdemo.db

import androidx.room.TypeConverter
import java.util.*

/**
 * @ Time  :  2020-08-28
 * @ Author :  helei
 * @ Email :   heleik@digitalchina.com
 * @ Description :
 */
class Converters {

    @TypeConverter
    fun calendarToDatestamp(calendar: Calendar): Long = calendar.timeInMillis


    @TypeConverter
    fun datastampToCalendar(time: Long): Calendar = Calendar.getInstance().apply {
        timeInMillis = time
    }

}