package com.kunlexze.giphy_gif_picker_native_android.database

import android.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.SmallTest
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
@SmallTest
class RoomTest {

    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()

    private lateinit var database: GiphyDatabase
    private lateinit var giphyDao: GiphyDao

    @Before
    fun setup() {
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            GiphyDatabase::class.java
        ).allowMainThreadQueries().build()
        giphyDao = database.giphyDao
    }

    @After
    fun tearDown() {

    }

    @Test
    fun gifIsNull() {
        val gif = giphyDao.getGifById("0")
        assertEquals(gif, null)
    }

    @Test
    fun gifsAreNull() {
        val gifs = giphyDao.getGifs().value;
        assertEquals(gifs, null)
    }

    @Test
    fun insertGifs() {
        giphyDao.insert(
            DatabaseGiphy(
                id = "1",
                type = "Type",
                title = "Title",
                imageUrl = "Image Url"
            )
        )
        val gifId = giphyDao.getGifById("1").id
        assertEquals(
            gifId, "1"
        )
    }

    @Test
    fun insertAllGifs() {
        giphyDao.insertAll(
            DatabaseGiphy(
                id = "1",
                type = "Type",
                title = "Title",
                imageUrl = "Image Url"
            ),
            DatabaseGiphy(
                id = "2",
                type = "Type",
                title = "Title",
                imageUrl = "Image Url"
            ),
            DatabaseGiphy(
                id = "3",
                type = "Type",
                title = "Title",
                imageUrl = "Image Url"
            )
        )
        val gifId = giphyDao.getGifById("1").id
        assertEquals(
            gifId, "1"
        )
    }
}