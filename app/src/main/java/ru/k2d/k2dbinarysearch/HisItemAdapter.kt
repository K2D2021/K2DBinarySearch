package ru.k2d.k2dbinarysearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.k2d.k2dbinarysearch.databinding.HistoryItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class HisItemAdapter : RecyclerView.Adapter<HisItemAdapter.HisItemHolder>() {
    val hisItemList = ArrayList<HisItem>()
    val hisItemList2 = ArrayList<HisItem>()

    class HisItemHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = HistoryItemBinding.bind(item)
        fun bind(hisItem: HisItem, hisItem2: HisItem) = with(binding) {
            historyItemText.text = hisItem.string
            historyItemText2.text = hisItem2.string
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HisItemHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HisItemHolder(view)
    }

    override fun onBindViewHolder(holder: HisItemHolder, position: Int) {
        holder.bind(hisItemList[position], hisItemList2[position])
    }

    override fun getItemCount(): Int {
        return hisItemList.size
    }

    fun addHisItem(getNumber: Int) {
        hisItemList.add(
            0,
            HisItem(LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm:ss")))
        )
        hisItemList2.add(0, HisItem("$getNumber"))
        notifyDataSetChanged()
    }

    fun addItemsOnStart(getDate: String, getNumber: Int) {
        hisItemList.add(0, HisItem(getDate))
        hisItemList2.add(0, HisItem("$getNumber"))
        notifyDataSetChanged()
    }
}