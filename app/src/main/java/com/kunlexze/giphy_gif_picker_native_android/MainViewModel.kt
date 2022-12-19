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

class MainViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    private var _gifs = MutableLiveData<List<GiphyModel>>(emptyList())
    val gifs: LiveData<List<GiphyModel>>
        get() = _gifs

    init {
        getGifs()
    }

    private fun getGifs() {

        coroutineScope.launch {
            var getDeferredGifs = GiphyApi.retrofitService.getGifs()

            try {
                var listResult = getDeferredGifs.await()
                _gifs.postValue(listResult.data)
            } catch (t: Throwable) {
                Log.d("TAG", "Throwable $t")
            }
        }

    }

    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }

}