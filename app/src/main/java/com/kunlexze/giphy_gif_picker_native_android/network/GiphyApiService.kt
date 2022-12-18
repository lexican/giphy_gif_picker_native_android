package com.kunlexze.giphy_gif_picker_native_android.network

import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

private const val BASE_URL = "https://api.giphy.com/"
private const val API_KEY = "5Hu274ziA4izsz1LH9KErh1t09jiw685"

var retrofit: Retrofit = Retrofit.Builder()
    .baseUrl(BASE_URL)
    .addConverterFactory(MoshiConverterFactory.create())
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface GiphyApiService {
    @GET("v1/gifs/trending?api_key=$API_KEY&limit=25&rating=g")
    fun getGifs():
            Deferred<GiphyResponse>
}

object GiphyApi {
    val retrofitService: GiphyApiService by lazy {
        retrofit.create(GiphyApiService::class.java)
    }
}