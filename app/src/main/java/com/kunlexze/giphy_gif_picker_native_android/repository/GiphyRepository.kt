package com.kunlexze.giphy_gif_picker_native_android.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.kunlexze.giphy_gif_picker_native_android.database.GiphyDatabase
import com.kunlexze.giphy_gif_picker_native_android.database.asDomainModel
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyApi
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyModel
import com.kunlexze.giphy_gif_picker_native_android.network.asDatabaseModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class GiphyRepository(private val database: GiphyDatabase) {
    val gifs: LiveData<List<GiphyModel>> = Transformations.map(database.giphyDao.getGifs()) {
        it.asDomainModel()
    }

    suspend fun refreshGifs(offset: Int = 0) {
        withContext(Dispatchers.IO) {
            val gifs = GiphyApi.retrofitService.getGifs(offset = offset).await()
            database.giphyDao.insertAll(*gifs.asDatabaseModel())
        }
    }
}