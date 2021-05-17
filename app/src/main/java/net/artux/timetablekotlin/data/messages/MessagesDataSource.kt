package net.artux.timetablekotlin.data.messages

import net.artux.timetablekotlin.data.model.Result
import net.artux.timetablekotlin.data.SSTUApiProvider
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
            Result.Error(
                IOException(
                    "Error getting messages",
                    e
                )
            )
        }
    }

    fun sendMessage(dlId: String, spId: String, text: String): Result<Int> {
        return try {
            val response = SSTUApiProvider.api.sendMessage(dlId, spId, text, "", "undefined").execute().body()

            if (response!=null){
                Result.Success(response)
            }else
                throw Exception("Can not send message")
        } catch (e: Throwable) {
            e.printStackTrace()
            Result.Error(
                IOException(
                    "Error sending messages",
                    e
                )
            )
        }
    }

}