package com.kunlexze.giphy_gif_picker_native_android

import android.app.Application
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.kunlexze.giphy_gif_picker_native_android.adapters.GifGridAdapter
import com.kunlexze.giphy_gif_picker_native_android.adapters.GifListener
import com.kunlexze.giphy_gif_picker_native_android.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        requireNotNull(applicationContext) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(
            this,
            MainViewModelFactory(applicationContext as Application)
        )[MainViewModel::class.java]

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.gifsGrid.adapter = GifGridAdapter(GifListener { gif ->
            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
            val clip = ClipData.newPlainText("label", gif.images.original.url)
            clipboard.setPrimaryClip(clip)
            Toast.makeText(this, "Gif url has been copied to clipboard", Toast.LENGTH_LONG)
                .show()
        })

        binding.viewModel = viewModel
        binding.lifecycleOwner = this

        binding.gifsGrid.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (!recyclerView.canScrollVertically(1)) {
                    var size = 0
                    if (viewModel.gifs.value?.size != null) {
                        size = viewModel.gifs.value?.size!!
                        if (viewModel.loadMore.value == false) {
                            viewModel.fetchGifs(offset = size)
                        }
                    } else {
                        viewModel.fetchGifs(offset = size)
                    }

                }
            }

        })

    }
}