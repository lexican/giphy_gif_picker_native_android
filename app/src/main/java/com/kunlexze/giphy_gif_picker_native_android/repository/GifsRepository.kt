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
import java.util.logging.Logger

class GifsRepository(val database: GiphyDatabase) {
    val gifs: LiveData<List<GiphyModel>> = Transformations.map(database.giphyDao.getGifs()) {
        it.asDomainModel();
    }

    suspend fun refreshGifs() {
        withContext(Dispatchers.IO) {
            val gifs = GiphyApi.retrofitService.getGifs().await()
            database.giphyDao.insertAll(*gifs.asDatabaseModel())
        }
    }
}