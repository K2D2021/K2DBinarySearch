package ru.k2d.k2dbinarysearch

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_db_history.view.*

class DBHistoryItemViewHolder(
    itemView: View,
    private val itemClickListener: DBHistoryItemAdapter.OnItemClickListener
) : RecyclerView.ViewHolder(itemView) {

    fun bind(dbHistoryItem: DBHistoryItem) {
        with(itemView) {
            dbHistoryItem.run {
                //nameTextView.text = id
                dateTextView.text = dateText
                descriptionTextView.text = guestedNumber

                /*deleteItemImageView.setOnClickListener {
                    itemClickListener.onItemClicked(id = id)
                }*/
            }
        }
    }

}