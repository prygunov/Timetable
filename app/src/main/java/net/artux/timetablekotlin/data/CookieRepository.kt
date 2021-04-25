package net.artux.timetablekotlin.data

import okhttp3.Cookie

object CookieRepository {

    private val cookies = ArrayList<Cookie>();

    init {
        val specificCookieBuilder = Cookie.Builder()
        specificCookieBuilder.name("PHPSESSID")
        specificCookieBuilder.value("hlsucqggam8ao6euhp3ciau7o1")
        specificCookieBuilder.httpOnly()
        specificCookieBuilder.domain("lk.samgtu.ru")
        specificCookieBuilder.path("/")
        cookies.add(specificCookieBuilder.build())
    }

    fun save(cookies: List<Cookie>){
        for (cookie in cookies) if (!this.cookies.contains(cookie))
            this.cookies.add(cookie)
    }

    fun load():List<Cookie>{
        return cookies;
    }

}