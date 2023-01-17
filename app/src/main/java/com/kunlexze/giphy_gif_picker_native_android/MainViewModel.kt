package com.kunlexze.giphy_gif_picker_native_android

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.kunlexze.giphy_gif_picker_native_android.database.getDatabase
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyApi
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyModel
import com.kunlexze.giphy_gif_picker_native_android.repository.GifsRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

     val database = getDatabase(application)
     val gifsRepository = GifsRepository(database)

    init {
        viewModelScope.launch {
            gifsRepository.refreshGifs()
        }
    }

    val gifs = gifsRepository.gifs;
}