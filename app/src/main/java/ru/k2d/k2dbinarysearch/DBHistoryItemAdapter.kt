package ru.k2d.k2dbinarysearch

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class DBHistoryItemAdapter(private val itemClickListener: OnItemClickListener) :
    RecyclerView.Adapter<DBHistoryItemViewHolder>() {

    private val dbHistoryItemsList = mutableListOf<DBHistoryItem>()

    @SuppressLint("NotifyDataSetChanged")
    fun addDBHistoryItem(dbHistoryItem: DBHistoryItem) {
        dbHistoryItemsList.add(dbHistoryItem)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setDBHistoryItems(dbHistoryItems: List<DBHistoryItem>){
        dbHistoryItemsList.clear()
        dbHistoryItemsList.addAll(dbHistoryItems)
        notifyDataSetChanged()
    }

    @SuppressLint("NotifyDataSetChanged")
    fun removeDBHistoryItem(id: String) {
        val dbHistoryItemToRemove = dbHistoryItemsList.find { it.id == id }
        dbHistoryItemToRemove?.let {
            dbHistoryItemsList.remove(it)
            notifyDataSetChanged()
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun deleteAllDBHistoryItems(){
        dbHistoryItemsList.clear()
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DBHistoryItemViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.item_db_history, parent, false)
        return DBHistoryItemViewHolder(itemView, itemClickListener)
    }

    override fun onBindViewHolder(holder: DBHistoryItemViewHolder, position: Int) {
        holder.bind(dbHistoryItemsList[position])
    }

    override fun getItemCount() = dbHistoryItemsList.size

    interface OnItemClickListener {
        fun onItemClicked(id: String)
    }

}