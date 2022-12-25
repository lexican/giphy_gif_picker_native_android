package com.kunlexze.giphy_gif_picker_native_android

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyApi
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

enum class ApiStatus { LOADING, ERROR, DONE }

class MainViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _gifs = MutableLiveData<List<GiphyModel>>(emptyList())
    val gifs: LiveData<List<GiphyModel>>
        get() = _gifs

    private val _status = MutableLiveData<ApiStatus>()
    val status: LiveData<ApiStatus>
        get() = _status

    init {
        getGifs()
    }

    private fun getGifs() {

        coroutineScope.launch {
            var getDeferredGifs = GiphyApi.retrofitService.getGifs()

            try {
                _status.value = ApiStatus.LOADING
                var listResult = getDeferredGifs.await()
                _status.value = ApiStatus.DONE
                _gifs.postValue(listResult.data)
            } catch (t: Throwable) {
                _status.value = ApiStatus.ERROR
                _gifs.value = ArrayList()
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}