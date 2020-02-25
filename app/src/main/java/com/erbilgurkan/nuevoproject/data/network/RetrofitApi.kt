package com.erbilgurkan.nuevoproject.data.network

import com.erbilgurkan.nuevoproject.data.models.FirstModel
import com.erbilgurkan.nuevoproject.data.models.SecondModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface RetrofitApi {

    @GET("photos")
    suspend fun getAllElements(): Response<List<FirstModel>>

    @GET("comments")
    suspend fun getDetail(@Query("postId") postId: Int): Response<List<SecondModel>>

    companion object {
        operator fun invoke(): RetrofitApi {
            val logging = HttpLoggingInterceptor()
            logging.level = (HttpLoggingInterceptor.Level.BODY)

            val client = OkHttpClient.Builder()
            client.addInterceptor(logging)


            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .client(client.build())
                .build()
                .create(RetrofitApi::class.java)
        }
    }
}