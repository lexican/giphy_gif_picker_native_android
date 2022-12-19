package com.kunlexze.giphy_gif_picker_native_android.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.kunlexze.giphy_gif_picker_native_android.databinding.GridViewItemBinding
import com.kunlexze.giphy_gif_picker_native_android.network.GiphyModel

class GifGridAdapter : ListAdapter<GiphyModel, GifGridAdapter.GifViewholder>(DiffCallback) {

    class GifViewholder(private var binding: GridViewItemBinding) : RecyclerView.ViewHolder(binding.root){
        fun bind(giphyModel: GiphyModel){
            binding.giphyModel = giphyModel
            binding.executePendingBindings()
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<GiphyModel>() {
        override fun areItemsTheSame(oldItem: GiphyModel, newItem: GiphyModel): Boolean {
            return oldItem === newItem
        }

        override fun areContentsTheSame(oldItem: GiphyModel, newItem: GiphyModel): Boolean {
            return oldItem.id == newItem.id
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): GifViewholder {
        return GifViewholder(GridViewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: GifViewholder, position: Int) {
        val marsProperty = getItem(position)
        holder.bind(marsProperty)
    }

}