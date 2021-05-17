package net.artux.timetablekotlin.data

class MessagesRepository(private val messagesDataSource: MessagesDataSource) {

    fun getMessages(id:String) = messagesDataSource.getMessages(id)

}