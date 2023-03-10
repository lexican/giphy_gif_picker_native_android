package com.kunlexze.giphy_gif_picker_native_android.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface GiphyDao {
    @Query("select * from databasegiphy")
    fun getGifs(): LiveData<List<DatabaseGiphy>>

    @Query("select * from databasegiphy where id=:key")
    fun getGifById(key: String): DatabaseGiphy

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(gif: DatabaseGiphy)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg gifs: DatabaseGiphy)

}

@Database(entities = [DatabaseGiphy::class], version = 1, exportSchema = false)
abstract class GiphyDatabase : RoomDatabase() {
    abstract val giphyDao: GiphyDao
}

private lateinit var INSTANCE: GiphyDatabase

fun getDatabase(context: Context): GiphyDatabase {
    synchronized(GiphyDatabase::class.java)
    {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                GiphyDatabase::class.java,
                "gifs"
            ).build()
        }
    }
    return INSTANCE
}


