package ru.k2d.k2dbinarysearch.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import ru.k2d.k2dbinarysearch.DBHistoryItem
@Database(
    entities = [DBHistoryItem::class], version = 1, exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun historyItemDAO(): HistoryItemDAO

    companion object{
        fun buildDatabase(context: Context, dbName: String): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, dbName).build()
        }
    }

}