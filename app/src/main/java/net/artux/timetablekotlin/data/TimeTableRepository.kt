package net.artux.timetablekotlin.data

import net.artux.timetablekotlin.data.main.Day
import java.text.SimpleDateFormat
import java.util.*

class TimeTableRepository(val dataSource: TimetableDataSource, val dateFormat: SimpleDateFormat) {

    suspend fun getDays(start:String, end:String):List<Day>{
        var result = dataSource.timetable(start, end)

        if (result is Result.Success){
            var list: MutableList<Day> = mutableListOf();
            var calendar = Calendar.getInstance(TimeZone.getDefault())

            for (occupation in result.data) {
                calendar.time = dateFormat.parse(occupation?.start);
                list.add(Day(calendar.get(Calendar.DAY_OF_MONTH), mutableListOf()))
            }

            for (occupation in result.data){
                calendar.time = dateFormat.parse(occupation?.start);


                occupation?.let {
                    for (day in list){

                        if (day.number == calendar.get(Calendar.DAY_OF_MONTH)) {
                            day.list.add(occupation);
                            break
                        }
                    }
                }
            }
            return list;
        }else return emptyList()
    }

    suspend fun getTask(id:String) = dataSource.getTask(id)
}