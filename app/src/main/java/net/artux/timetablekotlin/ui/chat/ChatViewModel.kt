package net.artux.timetablekotlin.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.artux.timetablekotlin.data.messages.MessagesRepository
import net.artux.timetablekotlin.data.model.Result
import net.artux.timetablekotlin.data.model.Message

class ChatViewModel (private val messagesRepository: MessagesRepository) : ViewModel() {

    val listResult = MutableLiveData<List<Message>>()
    var messageSent = MutableLiveData<Boolean>()

    fun getMessages(id:String){
        MainScope().launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext messagesRepository.getMessages(id)
            }

            if (result is Result.Success){
                listResult.value = result.data
            }
        }
    }

    fun sendMessage(dlId: String, spId: String, text: String){
        MainScope().launch {
            val result = withContext(Dispatchers.IO) {
                return@withContext messagesRepository.sendMessage(dlId, spId, text)
            }

            messageSent.value = result is Result.Success && result.data == 1
        }
    }

}