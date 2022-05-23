package ru.k2d.k2dbinarysearch

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import ru.k2d.k2dbinarysearch.databinding.HistoryItemBinding
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class RecyclerAdapter : RecyclerView.Adapter<RecyclerAdapter.HisItemHolder2>() {
    private val hisItemList = ArrayList<HisItem>()
    private val hisItemList2 = ArrayList<HisItem>()

    init {
        hisItemList.add(0, HisItem("test"))
        hisItemList2.add(0, HisItem("123"))
        addHisItem(5)
    }

    class HisItemHolder2(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = HistoryItemBinding.bind(item)
        fun bind2(hisItem: HisItem, hisItem2: HisItem) = with(binding) {
            historyItemText.text = hisItem.string
            historyItemText2.text = hisItem2.string
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HisItemHolder2 {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_item, parent, false)
        return HisItemHolder2(view)
    }

    override fun onBindViewHolder(holder: HisItemHolder2, position: Int) {
        holder.bind2(hisItemList[position], hisItemList2[position])
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