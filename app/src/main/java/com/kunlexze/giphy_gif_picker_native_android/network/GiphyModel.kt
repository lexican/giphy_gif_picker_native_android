package com.kunlexze.giphy_gif_picker_native_android.network

data class GiphyModel(
    val type: String,
    val id: String,
    val title: String,
    val images: GiphyImageResponse
)

data class GiphyImageResponse(val original: GiphyImageModel)

data class GiphyImageModel(val url: String)