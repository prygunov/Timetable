package net.artux.timetablekotlin.data

import net.artux.timetablekotlin.data.model.Message
import net.artux.timetablekotlin.data.model.Task
import okhttp3.*
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import retrofit2.http.Headers
import java.util.*

interface SSTUApiService {

    @FormUrlEncoded
    @POST("/site/login")
    fun login(
        @Field("LoginForm[username]") login: String?,
        @Field("LoginForm[password]") password: String?
    ): Call<Void?>?

    @GET("/api/common/distancelearning")
    @Headers("Referer:https://lk.samgtu.ru/distancelearning/distancelearning/index")
    fun timetable(
        @Query("start") start: String?,
        @Query("end") end: String?
    ): Call<ArrayList<Task?>?>?

    @GET("/distancelearning/distancelearning/view")
    fun getTask(@Query("id") id: String?): Call<ResponseBody?>?

    @GET("/api/common/distancelearningresults")
    @Headers("Referer:https://lk.samgtu.ru/distancelearning/distancelearning/index")
    fun getMessages(
        @Query("dl_id") dl_id: Int,
        @Query("dlp_id") dlp_id: Int
    ): Call<List<Message?>?>?

    companion object Factory {
        fun create(): SSTUApiService {
            val clientBuilder = OkHttpClient.Builder();
            clientBuilder.cookieJar(object : CookieJar {
                override fun saveFromResponse(
                    url: HttpUrl,
                    cookies: List<Cookie>
                ) {
                    CookieRepository.save(cookies);
                }

                override fun loadForRequest(url: HttpUrl): List<Cookie> {
                    return CookieRepository.load();
                }
            })


            val retrofit = Retrofit.Builder()
                .client(clientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://lk.samgtu.ru/")
                .build()

            return retrofit.create(SSTUApiService::class.java);
        }
    }

}