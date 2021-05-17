package net.artux.timetablekotlin.data.messages

import net.artux.timetablekotlin.data.messages.MessagesDataSource

class MessagesRepository(private val messagesDataSource: MessagesDataSource) {

    fun getMessages(id:String) = messagesDataSource.getMessages(id)

    fun sendMessage(dlId: String, spId: String, text: String) = messagesDataSource.sendMessage(dlId, spId, text)

}