package com.kunlexze.giphy_gif_picker_native_android

import android.app.Application
import androidx.lifecycle.*
import com.kunlexze.giphy_gif_picker_native_android.database.getDatabase
import com.kunlexze.giphy_gif_picker_native_android.repository.GiphyRepository
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel(application: Application) : AndroidViewModel(application) {

    private var _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    private var  _loadMore = MutableLiveData<Boolean>(false)
    val loadMore: LiveData<Boolean>
        get() = _loadMore

    private val database = getDatabase(application)
    private val gifsRepository = GiphyRepository(database)


    init {
        fetchGifs(offset = 0)
    }

    fun fetchGifs(offset: Int) {
        if(offset == 0){
            _status.value = ApiStatus.LOADING
            viewModelScope.launch {
                gifsRepository.refreshGifs(offset = offset)
                _status.value = ApiStatus.DONE
            }
        }else{
            _loadMore.value = true;
            viewModelScope.launch {
                gifsRepository.refreshGifs(offset = offset)
                _loadMore.value = false;
            }
        }

    }

    val gifs = gifsRepository.gifs

}