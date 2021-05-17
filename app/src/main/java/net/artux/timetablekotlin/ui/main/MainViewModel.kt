package net.artux.timetablekotlin.ui.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.artux.timetablekotlin.data.Result
import net.artux.timetablekotlin.data.TimeTableRepository
import net.artux.timetablekotlin.data.model.Day
import net.artux.timetablekotlin.data.model.Occupation
import java.text.SimpleDateFormat
import java.util.*

class MainViewModel (private val timetableRepository: TimeTableRepository, val dateFormat: SimpleDateFormat) : ViewModel() {

    val listResult = MutableLiveData<List<Day>>()
    val occupationData = MutableLiveData<Occupation>()
    private val cal = Calendar.getInstance()

    init {
        cal[Calendar.HOUR_OF_DAY] = 0 // ! clear would not reset the hour of day !

        cal.clear(Calendar.MINUTE)
        cal.clear(Calendar.SECOND)
        cal.clear(Calendar.MILLISECOND)
    }

    fun getTimetable(){
        MainScope().launch {
            val result = withContext(Dispatchers.IO) {

                cal.set(Calendar.DAY_OF_WEEK, cal.firstDayOfWeek)
                val start: String = dateFormat.format(cal.time)

                cal.add(Calendar.WEEK_OF_YEAR, 1)
                val end: String = dateFormat.format(cal.time)

                cal.add(Calendar.WEEK_OF_YEAR, -1)

                return@withContext timetableRepository.getDays(start, end)
            }
            listResult.value = result
        }
    }

    fun nextWeek(){
        cal.add(Calendar.WEEK_OF_YEAR, 1)
        getTimetable()
    }

    fun previousWeek(){
        cal.add(Calendar.WEEK_OF_YEAR, -1)
        getTimetable()
    }

    fun getTask(id:String){
        MainScope().launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext timetableRepository.getOccupation(id)
            }


            if (result is Result.Success){
                occupationData.value = result.data
            }
        }
    }

    fun getTaskId() = occupationData.value?.id
}