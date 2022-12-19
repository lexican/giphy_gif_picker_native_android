package com.kunlexze.giphy_gif_picker_native_android.adapters

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.kunlexze.giphy_gif_picker_native_android.R
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyModel

@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<GiphyModel>?) {
    val adapter = recyclerView.adapter as GifGridAdapter
    adapter.submitList(data)
}

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imgUrl: String?) {
    imgUrl?.let {
        val imgUri = imgUrl.toUri().buildUpon().scheme("https").build()
        Glide.with(imgView.context)
            .load(imgUri)
            .apply(
                RequestOptions()
                .placeholder(R.drawable.loading_animation)
                .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}