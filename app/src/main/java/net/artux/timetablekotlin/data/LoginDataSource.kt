package net.artux.timetablekotlin.data

import android.util.Log
import kotlinx.coroutines.isActive
import kotlinx.coroutines.suspendCancellableCoroutine
import net.artux.timetablekotlin.data.model.LoggedInUser
import java.io.IOException
import kotlin.coroutines.resume
import kotlin.coroutines.suspendCoroutine

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {

    suspend fun login(username: String, password: String): Result<LoggedInUser> {
        return suspendCancellableCoroutine {
            try {
                var loggedin = false
                SSTUApiProvider.api.login(username, password)?.execute()
                for(cookie in CookieRepository.load()){
                    if (cookie.name() == "_identity"){
                        val user = LoggedInUser(cookie.value(), username)
                        loggedin = true
                        if (it.isActive)
                            it.resume(Result.Success(user))
                    }
                }
                if (!loggedin)
                    throw Exception("Wrong username or password")
            } catch (e: Throwable) {
                e.printStackTrace()
                if (it.isActive)
                    it.resume(Result.Error(IOException("Error logging in", e)))
            }
        }

    }

    fun logout() {
        // TODO: revoke authentication
    }
}