package net.artux.timetablekotlin

import kotlinx.coroutines.*
import net.artux.timetablekotlin.data.LoginDataSource
import net.artux.timetablekotlin.data.LoginRepository
import net.artux.timetablekotlin.data.MessagesDataSource
import net.artux.timetablekotlin.data.MessagesRepository
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Test
    fun testLogin(){
        var loginRepository = LoginRepository(LoginDataSource())
        runBlocking {
            bar(loginRepository)
        }
        var messagesRepository = MessagesRepository(MessagesDataSource())
        runBlocking {
            println(messagesRepository.getMessages("290024"))
        }
    }

    suspend fun bar(loginRepository:LoginRepository) = coroutineScope {
        launch {
            loginRepository.login("prygunov.mi", "dirGe991850")
            println(loginRepository.isLoggedIn)
        }
    }

    @Test
    fun testMessages(){

    }
}