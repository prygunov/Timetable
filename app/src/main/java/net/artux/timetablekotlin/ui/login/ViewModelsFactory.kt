package net.artux.timetablekotlin.ui.login

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.artux.timetablekotlin.data.login.LoginDataSource
import net.artux.timetablekotlin.data.login.LoginRepository
import net.artux.timetablekotlin.data.messages.MessagesDataSource
import net.artux.timetablekotlin.data.messages.MessagesRepository
import net.artux.timetablekotlin.data.timetable.TimeTableRepository
import net.artux.timetablekotlin.data.timetable.TimetableDataSource
import net.artux.timetablekotlin.ui.chat.ChatViewModel
import net.artux.timetablekotlin.ui.main.MainViewModel
import java.text.SimpleDateFormat
import java.util.*

class ViewModelsFactory : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                    loginRepository = LoginRepository(
                        dataSource = LoginDataSource()
                    )
            ) as T
        }else if(modelClass.isAssignableFrom(MainViewModel::class.java)) {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss",
                    Locale.US)
            return MainViewModel(
                    timetableRepository = TimeTableRepository(
                        dataSource = TimetableDataSource(),
                        dateFormat = dateFormat
                    ),
                    dateFormat = dateFormat
            ) as T
        }else if(modelClass.isAssignableFrom(ChatViewModel::class.java)){
            return ChatViewModel(messagesRepository = MessagesRepository(
                MessagesDataSource()
            )
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
