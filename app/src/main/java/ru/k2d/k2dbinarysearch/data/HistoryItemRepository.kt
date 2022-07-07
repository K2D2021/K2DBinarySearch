package ru.k2d.k2dbinarysearch.data


import ru.k2d.k2dbinarysearch.DBHistoryItem
import ru.k2d.k2dbinarysearch.DBHistoryItemID

interface HistoryItemRepository {

    suspend fun insert(dbHistoryItem: DBHistoryItem)

    suspend fun getAllHistoryItems(): List<DBHistoryItem>

    suspend fun deleteById(id: DBHistoryItemID)

    suspend fun deleteAll()
}