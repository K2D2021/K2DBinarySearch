package ru.k2d.k2dbinarysearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_history.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.k2d.k2dbinarysearch.DBHistoryItemAdapter
import ru.k2d.k2dbinarysearch.MainActivity
import ru.k2d.k2dbinarysearch.R

class HistoryFragment : Fragment(), DBHistoryItemAdapter.OnItemClickListener {

    private lateinit var dbHistoryItemAdapter: DBHistoryItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecyclerView()

        super.onViewCreated(view, savedInstanceState)

        buttonClearHistory.setOnClickListener {
            deleteAllHistoryItems()
        }

        retrieveDBHistoryItems()
    }


    override fun onItemClicked(id: String) {
        TODO("Not yet implemented")
    }

    private fun initRecyclerView() {
        dbHistoryItemAdapter = DBHistoryItemAdapter(this)

        with(rcView) {
            val tryLayout = LinearLayoutManager(context)
            tryLayout.reverseLayout = true
            tryLayout.stackFromEnd = true
            this.layoutManager = tryLayout
            this.adapter = dbHistoryItemAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun retrieveDBHistoryItems() {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            val dbHistoryItems = (requireContext() as MainActivity).repository.getAllHistoryItems()
            // Work on main thread
            withContext(Dispatchers.Main) {
                dbHistoryItemAdapter.setDBHistoryItems(dbHistoryItems)
            }
        }
    }

    private fun deleteAllHistoryItems() {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            (requireContext() as MainActivity).repository.deleteAll()
            withContext(Dispatchers.Main) {
                dbHistoryItemAdapter.deleteAllDBHistoryItems()
            }
        }
    }
}
