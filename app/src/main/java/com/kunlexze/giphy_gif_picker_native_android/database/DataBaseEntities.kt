package com.kunlexze.giphy_gif_picker_native_android.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyImageModel
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyImageResponse
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyModel

@Entity
data class DatabaseGiphy constructor(
    @PrimaryKey
    val id: String,
    val type: String,
    val title: String,
    val imageUrl: String
)

fun List<DatabaseGiphy>.asDomainModel(): List<GiphyModel> {
    return map {
        GiphyModel(
            type = it.type,
            id = it.id,
            title = it.title,
            images = GiphyImageResponse(original = GiphyImageModel(url = it.imageUrl))
        )
    }
}