package ru.k2d.k2dbinarysearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_history.*
import ru.k2d.k2dbinarysearch.MainActivity
import ru.k2d.k2dbinarysearch.R


class HistoryFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        buttonBackOrNewGame.setOnClickListener {
            (activity as MainActivity).replaceFragment(GameFragment())

        }

    }
    
}

/*
open class FullHistory : AppCompatActivity() {
    private lateinit var binding: FullHistoryBinding
    private val adapter = HisItemAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FullHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        loadData()
        init()

        buttonClearHistory.setOnClickListener {
            val myDialogFragment = AcceptToClearHistory()
            val manager: FragmentManager = supportFragmentManager
            myDialogFragment.show(manager, "dialog")
        }

        buttonBackOrNewGame.setOnClickListener {
            val intent3 = Intent(this, BinarySearch::class.java).apply {
            }
            startActivity(intent3)
            finish()
        }
    }

    private fun init() {
        binding.apply {
            val myLinearLayoutManager = object : LinearLayoutManager(this@FullHistory) {
                override fun canScrollVertically(): Boolean {
                    return true
                }
            }
            rcView.layoutManager = myLinearLayoutManager
            rcView.adapter = adapter
        }
    }

    private fun loadData() {
        val sharedPreference = SharedPreferences(this)
        sharedPreference.getAllData()?.toSortedMap()?.forEach {
            adapter.addItemsOnStart(it.key, it.value as Int)
            adapter.notifyDataSetChanged()
        }
    }

    fun clearData() {
        val sharedPreference = SharedPreferences(this)
        sharedPreference.clearSharedPreference()
        adapter.hisItemList.clear()
        adapter.hisItemList2.clear()
        adapter.notifyDataSetChanged()
    }
}
 */