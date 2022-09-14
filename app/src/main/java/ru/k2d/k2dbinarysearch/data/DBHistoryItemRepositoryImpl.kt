package ru.k2d.k2dbinarysearch.data

import ru.k2d.k2dbinarysearch.data.models.DBHistoryItem
import ru.k2d.k2dbinarysearch.DBHistoryItemID

class DBHistoryItemRepositoryImpl(private val dbHistoryItemDAO: HistoryItemDAO) :
    HistoryItemRepository {
    override suspend fun insert(dbHistoryItem: DBHistoryItem) {
        dbHistoryItemDAO.insert(dbHistoryItem)
    }

    override suspend fun getAllHistoryItems() = dbHistoryItemDAO.getAllDBHistoryItems()

    override suspend fun deleteById(id: DBHistoryItemID) {
        dbHistoryItemDAO.deleteById(id)
    }

    override suspend fun deleteAll() = dbHistoryItemDAO.deleteAllDBHistoryItems()

}