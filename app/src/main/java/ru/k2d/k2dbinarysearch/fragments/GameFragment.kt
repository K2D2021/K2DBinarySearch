package ru.k2d.k2dbinarysearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_game.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.k2d.k2dbinarysearch.*
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class GameFragment : Fragment(), DBHistoryItemAdapter.OnItemClickListener {

    private lateinit var dbHistoryItemAdapter: DBHistoryItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)

        /*
        val view = inflater.inflate(R.layout.fragment_game, container, false)

        //view.recyclerView.layoutManager = LinearLayoutManager(activity)
        view.rcViewF.layoutManager = LinearLayoutManager(activity)
//        view.recyclerView.adapter = MainAdapter()
        dbHistoryItemAdapter = DBHistoryItemAdapter(this)
        view.rcViewF.adapter = dbHistoryItemAdapter

        /*
        this.layoutManager = LinearLayoutManager(context)
            this.adapter = dbHistoryItemAdapter

         */


        return view*/

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecyclerView()

        super.onViewCreated(view, savedInstanceState)

        val x = IntArray(1000001) { it }
        var min = 0
        var max = x.size - 1
        var count = 1
        var mid = (0..x.size).random() // var mid = (min + max)/2 original version
        var guess = x[mid]

        //loadData()
        val isItThatNumber = getString(R.string.is_it_that_number) + " " + guess + "?"
        val okAttempt =
            getString(R.string.ok_attempt) + count + getString(R.string.be_better) + isItThatNumber
        val brokeSomething =
            getString(R.string.o_no_it_is_attempt) + count + getString(R.string.broke_something)
        val softHuman =
            getString(R.string.i_knew_it_was) + guess + getString(R.string.soft_human) + count + getString(
                R.string.tries_ha_ha
            )
//        guestextF.text = isItThatNumber
        guestextF.text = guess.toString()
        buttonLessF.setOnClickListener {
            max = mid - 1
            mid = (min + max) / 2
            guess = x[mid]
            guestextF.text = okAttempt
            if (count >= 21) {
                guestextF.text = brokeSomething
                buttonYesF.isClickable = false
                buttonYesF.isEnabled = false
                buttonLessF.isClickable = false
                buttonLessF.isEnabled = false
                buttonBigF.isClickable = false
                buttonBigF.isEnabled = false
            }
            count++
        }
        buttonYesF.setOnClickListener {
            guestextF.text = softHuman
            //rcViewTest(guess)
            buttonYesF.isClickable = false
            buttonYesF.isEnabled = false
            buttonLessF.isClickable = false
            buttonLessF.isEnabled = false
            buttonBigF.isClickable = false
            buttonBigF.isEnabled = false
//            saveData(
//                LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm:ss")),
//                guess
//            )
            val dbHistoryItem = DBHistoryItem(
                id = LocalDateTime.now().toString(),
                dateText = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm:ss")).toString(),
                //guestedNumber = kotlin.random.Random.nextInt(10000..100000).toString(),
                guestedNumber = guess.toString(),
            )
            dbHistoryItemAdapter.addDBHistoryItem(dbHistoryItem)

            insertHistoryItem(dbHistoryItem)


        }
        buttonBigF.setOnClickListener {
            min = mid + 1
            mid = (min + max) / 2
            guess = x[mid]
            guestextF.text = okAttempt
            if (count >= 25) {
                guestextF.text = brokeSomething
                buttonYesF.isClickable = false
                buttonYesF.isEnabled = false
                buttonLessF.isClickable = false
                buttonLessF.isEnabled = false
                buttonBigF.isClickable = false
                buttonBigF.isEnabled = false
            }
            count++
        }
        buttonNewGameF.setOnClickListener {
            min = 0
            max = x.size - 1
            count = 1
            mid = (0..x.size).random() // var mid = (min + max)/2 original version
            guess = x[mid]
            guestextF.text = isItThatNumber
            buttonYesF.isClickable = true
            buttonYesF.isEnabled = true
            buttonLessF.isClickable = true
            buttonLessF.isEnabled = true
            buttonBigF.isClickable = true
            buttonBigF.isEnabled = true
        }
        imageViewF.setOnClickListener {
            (activity as MainActivity).replaceFragment(HistoryFragment())
            (activity as MainActivity).newWayToChangeFragment(HistoryFragment())
        }

        retrieveDBHistoryItems()
    }

    override fun onItemClicked(id: DBHistoryItemID) {
        /*alertDialog = AlertDialog.Builder(this)
            .setTitle("Delete Person")
            .setMessage("Do you want to delete the person from the list")
            .setPositiveButton("Yes") { dialog, _ ->
                deletePersonById(id)
                dialog.dismiss()
            }.setNegativeButton("No") { dialog, _ ->
                dialog.dismiss()
            }
            .show()*/
    }

    private fun initRecyclerView() {
        dbHistoryItemAdapter = DBHistoryItemAdapter(this)

        with(rcViewF) {
            this.layoutManager = LinearLayoutManager(context)
            this.adapter = dbHistoryItemAdapter
            this.setHasFixedSize(true)
        }
    }

    private fun insertHistoryItem(historyItem: DBHistoryItem) {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
            (requireContext() as MainActivity).repository.insert(dbHistoryItem = historyItem)
        }
    }

    private fun retrieveDBHistoryItems() {
        // Work on background thread
        lifecycleScope.launch(Dispatchers.IO) {
           // val dbHistoryItems = (applicationContext as App).repository.getAllHistoryItems()
            val dbHistoryItems = (requireContext() as MainActivity).repository.getAllHistoryItems()
            // Work on main thread

            withContext(Dispatchers.Main) {
                dbHistoryItemAdapter.setDBHistoryItems(dbHistoryItems)
            }
        }
    }


//    private fun rcViewTest(getNumber: Int) {
//        rcViewF.adapter = adapter
//        adapter.addHisItem(getNumber) // it save numbr to list but at the moment it must save to shared pref and get it from shared pref
//        adapter.notifyDataSetChanged()
//    }

//    private fun saveData(date: String, value: Int) {
//        val sharedPreference = SharedPreferences(requireContext())
//        sharedPreference.save(date, value)
//    }

//    private fun loadData() {
//        val sharedPreference = SharedPreferences(requireContext())
//        sharedPreference.getAllData()?.toSortedMap()?.forEach {
//            adapter.addItemsOnStart(it.key, it.value as Int)
//            adapter.notifyDataSetChanged()
//        }
//    }
}