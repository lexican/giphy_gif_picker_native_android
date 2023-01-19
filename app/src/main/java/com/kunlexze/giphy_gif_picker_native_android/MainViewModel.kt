package com.kunlexze.giphy_gif_picker_native_android

import android.app.Application
import androidx.lifecycle.*
import com.kunlexze.giphy_gif_picker_native_android.database.getDatabase
import com.kunlexze.giphy_gif_picker_native_android.repository.GiphyRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {
    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private val database = getDatabase(application)
    private val gifsRepository = GiphyRepository(database)


    init {
        fetchGifs(offset = 0)
    }

    fun fetchGifs(offset: Int) {
        viewModelScope.launch {
            gifsRepository.refreshGifs(offset = offset)
        }
    }

    val gifs = gifsRepository.gifs

}