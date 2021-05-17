package net.artux.timetablekotlin.data

import net.artux.timetablekotlin.data.model.Day
import java.text.SimpleDateFormat
import java.util.*

class TimeTableRepository(val dataSource: TimetableDataSource, val dateFormat: SimpleDateFormat) {

    suspend fun getDays(start:String, end:String):List<Day>{
        val result = dataSource.timetable(start, end)

        if (result is Result.Success){
            val list: MutableList<Day> = mutableListOf()
            val calendar = Calendar.getInstance(TimeZone.getDefault())

            for (occupation in result.data) {
                calendar.time = dateFormat.parse(occupation.start)
                list.add(
                    Day(
                        calendar.get(
                            Calendar.DAY_OF_MONTH
                        ), mutableListOf()
                    )
                )
            }

            for (occupation in result.data){
                calendar.time = dateFormat.parse(occupation.start)

                for (day in list)
                    if (day.number == calendar.get(Calendar.DAY_OF_MONTH)) {
                        day.list.add(occupation)
                        break
                    }


            }
            return list
        }else return emptyList()
    }

    suspend fun getOccupation(id:String) = dataSource.getOccupation(id)
}