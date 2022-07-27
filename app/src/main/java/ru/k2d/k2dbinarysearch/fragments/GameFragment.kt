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
import java.security.SecureRandom
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class GameFragment : Fragment(), DBHistoryItemAdapter.OnItemClickListener {

    private lateinit var dbHistoryItemAdapter: DBHistoryItemAdapter
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_game, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        initRecyclerView()

        super.onViewCreated(view, savedInstanceState)

        val x = IntArray(1000001) { it }
        var min = 0
        var max = x.size - 1
        var count = 1
        var mid = getFirstRandom(x.size) // var mid = (min + max)/2 original version
        var guess = x[mid]


        guestextF.text = isItThatNumber(guess)
        buttonLessF.setOnClickListener {
            max = mid - 1
            mid = (min + max) / 2
            guess = x[mid]
            guestextF.text = okAttempt(count, isItThatNumber(guess))
            checkAttemptCount(count)
            count++
        }
        buttonYesF.setOnClickListener {
            guestextF.text = correctAnswer(guess, count)
            changeStateButtonsExceptNewGame()
            val dbHistoryItem = DBHistoryItem(
                id = LocalDateTime.now().toString(),
                dateText = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm:ss")).toString(),
                guestedNumber = guess.toString(),
            )
            dbHistoryItemAdapter.addDBHistoryItem(dbHistoryItem)

            insertHistoryItem(dbHistoryItem)


        }
        buttonBigF.setOnClickListener {
            min = mid + 1
            mid = (min + max) / 2
            guess = x[mid]
            guestextF.text = okAttempt(count, isItThatNumber(guess))
            checkAttemptCount(count)
            count++
        }
        buttonNewGameF.setOnClickListener {
            min = 0
            max = x.size - 1
            count = 1
            mid = (0..x.size).random() // var mid = (min + max)/2 original version
            guess = x[mid]
            guestextF.text = isItThatNumber(guess)
            changeStateButtonsExceptNewGame(true)
        }
        imageViewF.setOnClickListener {
            (activity as MainActivity).replaceFragment(HistoryFragment())
            (activity as MainActivity).newWayToChangeFragment(HistoryFragment())
        }

        retrieveDBHistoryItems()
    }

    override fun onItemClicked(id: DBHistoryItemID) {
        // TODO: delete this from parent
    }

    private fun initRecyclerView() {
        dbHistoryItemAdapter = DBHistoryItemAdapter(this)

        with(rcViewF) {
            val tryLayout = LinearLayoutManager(context)
            tryLayout.reverseLayout = true
            tryLayout.stackFromEnd = true
            this.layoutManager = tryLayout
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
            val dbHistoryItems = (requireContext() as MainActivity).repository.getAllHistoryItems()
            // Work on main thread
            withContext(Dispatchers.Main) {
                dbHistoryItemAdapter.setDBHistoryItems(dbHistoryItems)
            }
        }
    }

    private fun getFirstRandom(upperLimit: Int) = SecureRandom().nextInt(upperLimit)

    private fun isItThatNumber(guess: Int) =
        getString(R.string.is_it_that_number) + " " + guess + "?"

    private fun okAttempt(count: Int, isItThantNumberResult: String) =
        getString(R.string.ok_attempt) + " " + count + " " + getString(R.string.be_better) + " " + isItThantNumberResult

    private fun toMuchAttempts(count: Int) =
        getString(R.string.o_no_it_is_attempt) + " " + count + getString(R.string.broke_something)

    private fun correctAnswer(guess: Int, count: Int) =
        getString(R.string.i_knew_it_was) + " " + guess + getString(R.string.soft_human) + " " + count + " " + getString(
            R.string.tries_ha_ha
        )

    private fun changeStateButtonsExceptNewGame(state: Boolean = false) {
        buttonYesF.isClickable = state
        buttonYesF.isEnabled = state
        buttonLessF.isClickable = state
        buttonLessF.isEnabled = state
        buttonBigF.isClickable = state
        buttonBigF.isEnabled = state
    }

    private fun checkAttemptCount(count: Int) {
        if (count >= 21) {
            guestextF.text = toMuchAttempts(count)
            changeStateButtonsExceptNewGame()
        }
    }
}