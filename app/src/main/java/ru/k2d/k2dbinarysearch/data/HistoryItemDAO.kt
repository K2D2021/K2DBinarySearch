package ru.k2d.k2dbinarysearch.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import ru.k2d.k2dbinarysearch.data.models.DBHistoryItem

@Dao
interface HistoryItemDAO {
    @Insert
    suspend fun insert(dbHistoryItem: DBHistoryItem)

    @Query("SELECT * FROM ${DBHistoryItem.TABLE_NAME}")
    suspend fun getAllDBHistoryItems(): List<DBHistoryItem>

    @Query("DELETE FROM ${DBHistoryItem.TABLE_NAME} WHERE ${DBHistoryItem.ID} = :id")
    suspend fun deleteById(id: String)

    @Query("DELETE FROM ${DBHistoryItem.TABLE_NAME}")
    suspend fun deleteAllDBHistoryItems()
}