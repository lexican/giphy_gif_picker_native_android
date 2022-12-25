package com.kunlexze.giphy_gif_picker_native_android

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.kunlexze.giphy_gif_picker_native_android.adapters.GifGridAdapter
import com.kunlexze.giphy_gif_picker_native_android.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val binding =
            DataBindingUtil.setContentView<ActivityMainBinding>(this, R.layout.activity_main)

        binding.gifsGrid.adapter = GifGridAdapter()

        binding.viewModel = viewModel
        binding.lifecycleOwner = this


    }
}