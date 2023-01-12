package com.kunlexze.giphy_gif_picker_native_android.network

import com.kunlexze.giphy_gif_picker_native_android.database.DatabaseGiphy

data class NetworkGiphyContainer(val gifs: List<GiphyModel>)

fun NetworkGiphyContainer.asDatabaseModel(): List<DatabaseGiphy> {
    return gifs.map {
        DatabaseGiphy(
            id = it.id,
            type = it.type,
            title = it.title,
            imageUrl = it.images.original.url
        )
    }
}