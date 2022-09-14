package ru.k2d.k2dbinarysearch.data.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.k2d.k2dbinarysearch.data.models.DBHistoryItem.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME

)
data class DBHistoryItem(
    @PrimaryKey
    val id: String,
    val guestedNumber: String,
    val dateText: String
) {

    companion object {
        const val TABLE_NAME = "DBHistoryItem"
        const val ID = "Id"
    }
}