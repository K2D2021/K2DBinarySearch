package ru.k2d.k2dbinarysearch

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class DBHistoryItemViewHolder(
    itemView: View
) : RecyclerView.ViewHolder(itemView) {

    fun bind(dbHistoryItem: DBHistoryItem) {
        with(itemView) {
            dbHistoryItem.run {
                val dateTextView = findViewById<TextView>(R.id.dateTextView)
                val numberTextView = findViewById<TextView>(R.id.numberTextView)
                dateTextView.text = dateText
                numberTextView.text = guestedNumber
            }
        }
    }

}