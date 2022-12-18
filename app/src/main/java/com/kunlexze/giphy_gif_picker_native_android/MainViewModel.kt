package com.kunlexze.giphy_gif_picker_native_android

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class MainViewModel : ViewModel() {

    private var viewModelJob = Job()
    private val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)

    init {
        getGifs()
    }


    private fun getGifs() {

        coroutineScope.launch {
            var getDeferredGifs = GiphyApi.retrofitService.getGifs()

            try {
                var listResult = getDeferredGifs.await()
                Log.d(
                    "TAG",
                    "listResult  ${listResult.toString()}"
                )
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