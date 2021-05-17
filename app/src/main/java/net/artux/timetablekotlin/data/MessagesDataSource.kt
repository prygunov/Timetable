package net.artux.timetablekotlin.data

import net.artux.timetablekotlin.data.model.Decision
import net.artux.timetablekotlin.data.model.Message
import java.io.IOException

class MessagesDataSource {

    fun getMessages(id: String): Result<List<Message>> {
        return try {
            val response = SSTUApiProvider.api.getMessages(id).execute().body()

            if (response!=null){
                response.reverse();
                Result.Success(response)
            }else
                throw Exception("Can not get messages")
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(IOException("Error getting messages", e))
        }
    }

}