package ru.k2d.k2dbinarysearch

import androidx.room.Entity
import androidx.room.PrimaryKey
import ru.k2d.k2dbinarysearch.DBHistoryItem.Companion.TABLE_NAME

@Entity(
    tableName = TABLE_NAME

)
data class DBHistoryItem(
    @PrimaryKey
    val id: String,
    //val dateTime: String,
    val guestedNumber: String,
    //val avatarUrl: String,
    val dateText: String
) {

    companion object {
        const val TABLE_NAME = "DBHistoryItem"

        const val ID = "Id"
        //const val NAME = "Name"
        const val guessedNumber = "guessedNumber"
        //const val AVATAR_URL = "AvatarUrl"

        const val DATETEXT = "dateText"

    }
}