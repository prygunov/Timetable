package net.artux.timetablekotlin.data

import kotlinx.coroutines.suspendCancellableCoroutine
import net.artux.timetablekotlin.data.model.Task
import okhttp3.Response
import okhttp3.ResponseBody
import java.io.IOException
import kotlin.coroutines.resume

class TimetableDataSource {

    suspend fun timetable(startTime: String, endTime: String): Result<List<Task?>> {
        return suspendCancellableCoroutine {
            try {
                val response = SSTUApiProvider.api.timetable(startTime, endTime)?.execute()

                val list = response?.body();
                if (list!=null && it.isActive){
                    it.resume(Result.Success(list))
                }else
                    throw Exception("Can not get timetable")
            } catch (e: Throwable) {
                e.printStackTrace()
                if (it.isActive)
                    it.resume(Result.Error(IOException("Error getting timetable", e)))
            }
        }
    }

    suspend fun getTask(id: String): Result<String> {
        return suspendCancellableCoroutine {
            try {
                val response = SSTUApiProvider.api.getTask(id)?.execute()?.body()

                if (response!=null && it.isActive){
                    it.resume(Result.Success(response.string()))
                }else
                    throw Exception("Can not get task page")
            } catch (e: Throwable) {
                e.printStackTrace()
                if (it.isActive)
                    it.resume(Result.Error(IOException("Error getting timetable", e)))
            }
        }
    }

}