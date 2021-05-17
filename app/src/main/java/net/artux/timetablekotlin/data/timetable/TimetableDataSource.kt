package net.artux.timetablekotlin.data.timetable

import kotlinx.coroutines.suspendCancellableCoroutine
import net.artux.timetablekotlin.data.SSTUApiProvider
import net.artux.timetablekotlin.data.model.Decision
import net.artux.timetablekotlin.data.model.Occupation
import net.artux.timetablekotlin.data.model.OccupationItem
import net.artux.timetablekotlin.data.model.Result
import net.artux.timetablekotlin.data.model.Task
import org.jsoup.Jsoup
import java.io.IOException
import kotlin.coroutines.resume

class TimetableDataSource {

    suspend fun timetable(startTime: String, endTime: String): Result<List<OccupationItem>> {
        return suspendCancellableCoroutine {
            try {
                val response = SSTUApiProvider.api.timetable(startTime, endTime).execute()

                val list = response.body()
                if (list!=null && it.isActive){
                    it.resume(Result.Success(list))
                }else
                    throw Exception("Can not get timetable $response" )
            } catch (e: Throwable) {
                e.printStackTrace()
                if (it.isActive)
                    it.resume(Result.Error(IOException("Error getting timetable", e)))
            }
        }
    }

    suspend fun getOccupation(id: String): Result<Occupation> {
        return suspendCancellableCoroutine {
            try {
                val htmlOccupation = SSTUApiProvider.api.getOccupation(id).execute().body()

                if (htmlOccupation!=null && it.isActive){
                    val htmlSite: String = htmlOccupation.string()
                    val doc = Jsoup.parse(htmlSite)
                    val values = doc.select("td")
                    var teacher = ""
                    var title = ""
                    var type = ""
                    var date = ""
                    var time = ""
                    var place = ""
                    var groups = listOf<String>()
                    var i = 0
                    while (i < values.size){
                        when (i) {
                            0 -> teacher = values[i].text()
                            1 -> title = values[i].text()
                            2 -> type = values[i].text()
                            3 -> date = values[i].text()
                            4 -> time = values[i].text()
                            5 -> place = values[i].text()
                            6 -> groups = values[i].text().split(",")
                        }
                        i++
                    }
                    val studentId = doc.select("div[ng-init]")
                        .first().attr("ng-init")
                        .substringAfter(", ")
                        .substringBefore(")")
                    val occupation = Occupation(id, studentId, teacher, title, type, date, time, place, groups)

                    val blocks = doc.select("div.grid-view")

                    for (block in blocks){
                        when {
                            block.id() == "w1" -> {
                                val links  = blocks.select("table.table.table-striped.table-bordered > tbody > tr > td > a")
                                val texts = links.eachText()
                                val hrefs = links.eachAttr("href")
                                var map = HashMap<String, String>()
                                for((index, value) in hrefs.iterator().withIndex()){
                                    map[texts[index]] = value.substringAfter("id=")
                                }
                                occupation.links = map
                            }
                            block.hasAttr("ng-init") -> {
                                val taskId = block.attr("ng-init").substringAfter("(").substringBefore(")")
                                val desc = block.html().substringAfter("</h2>").substringBefore("<table")
                                val decisions = getDecisions(taskId)
                                if (decisions is Result.Success){
                                    val task = Task(desc, decisions.data)
                                    occupation.task = task
                                }
                            }
                            else -> occupation.description = block.html()
                        }
                    }

                    println(occupation)

                    it.resume(Result.Success(occupation))
                }else
                    throw Exception("Can not get task page")
            } catch (e: Throwable) {
                e.printStackTrace()
                if (it.isActive)
                    it.resume(Result.Error(IOException("Error getting timetable", e)))
            }
        }
    }

    private fun getDecisions(id: String): Result<List<Decision>> {
        return try {
            val response = SSTUApiProvider.api.getDecisions(id).execute().body()

            if (response!=null){
                Result.Success(response)
            }else
                throw Exception("Can not get task page")
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(IOException("Error getting timetable", e))
        }
    }

}