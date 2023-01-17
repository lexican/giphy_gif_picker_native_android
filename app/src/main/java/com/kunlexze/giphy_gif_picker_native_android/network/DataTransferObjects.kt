package com.kunlexze.giphy_gif_picker_native_android.network

import com.kunlexze.giphy_gif_picker_native_android.database.DatabaseGiphy

data class NetworkGiphyContainer(val data: List<GiphyModel>)

fun NetworkGiphyContainer.asDatabaseModel(): Array<DatabaseGiphy> {
    return data.map {
        DatabaseGiphy(
            id = it.id,
            type = it.type,
            title = it.title,
            imageUrl = it.images.original.url
        )
    }.toTypedArray()
}