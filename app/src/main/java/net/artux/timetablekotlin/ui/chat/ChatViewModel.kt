package net.artux.timetablekotlin.ui.chat

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.artux.timetablekotlin.data.MessagesRepository
import net.artux.timetablekotlin.data.Result
import net.artux.timetablekotlin.data.TimeTableRepository
import net.artux.timetablekotlin.data.model.Day
import net.artux.timetablekotlin.data.model.Message
import java.text.SimpleDateFormat

class ChatViewModel (private val messagesRepository: MessagesRepository) : ViewModel() {

    val listResult = MutableLiveData<List<Message>>()

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

}