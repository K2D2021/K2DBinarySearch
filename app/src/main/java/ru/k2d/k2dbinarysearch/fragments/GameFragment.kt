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
import ru.k2d.k2dbinarysearch.DBHistoryItem
import ru.k2d.k2dbinarysearch.DBHistoryItemAdapter
import ru.k2d.k2dbinarysearch.MainActivity
import ru.k2d.k2dbinarysearch.R
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter


class GameFragment : Fragment() {

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

        val baseSortedArray = IntArray(1000001) { it }
        var leftBorder = 0
        var rightBorder = baseSortedArray.size - 1
        var attemptsCounter = 1
        var middlePosition = (activity as MainActivity).getFirstRandom(baseSortedArray.size)
        var guess = baseSortedArray[middlePosition]

        gameFragmentTopText.text = isItThatNumber(guess)
        buttonItLess.setOnClickListener {
            rightBorder = middlePosition - 1
            middlePosition = (leftBorder + rightBorder) / 2
            guess = baseSortedArray[middlePosition]
            gameFragmentTopText.text = okAttempt(attemptsCounter, isItThatNumber(guess))
            checkAttemptCount(attemptsCounter)
            attemptsCounter++
        }
        buttonItIs.setOnClickListener {
            gameFragmentTopText.text = correctAnswer(guess, attemptsCounter)
            changeStateButtonsExceptNewGame()
            val dbHistoryItem = DBHistoryItem(
                id = LocalDateTime.now().toString(),
                dateText = LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("dd.MM.yy   HH:mm:ss")).toString(),
                guestedNumber = guess.toString(),
            )
            dbHistoryItemAdapter.addDBHistoryItem(dbHistoryItem)

            insertHistoryItem(dbHistoryItem)
            recyclerScrollToTop()
        }

        buttonItBigger.setOnClickListener {
            leftBorder = middlePosition + 1
            middlePosition = (leftBorder + rightBorder) / 2
            guess = baseSortedArray[middlePosition]
            gameFragmentTopText.text = okAttempt(attemptsCounter, isItThatNumber(guess))
            checkAttemptCount(attemptsCounter)
            attemptsCounter++
        }

        buttonNewGame.setOnClickListener {
            leftBorder = 0
            rightBorder = baseSortedArray.size - 1
            attemptsCounter = 1
            middlePosition = (activity as MainActivity).getFirstRandom(baseSortedArray.size)
            guess = baseSortedArray[middlePosition]
            gameFragmentTopText.text = isItThatNumber(guess)
            changeStateButtonsExceptNewGame(true)
        }

        historyOverlayImageShadowEffect.setOnClickListener {
            (activity as MainActivity).replaceFragment(HistoryFragment())
            (activity as MainActivity).newWayToChangeFragment(HistoryFragment())
        }

        retrieveDBHistoryItems()
    }

    private fun initRecyclerView() {
        dbHistoryItemAdapter = DBHistoryItemAdapter()

        with(recyclerViewGameFragment) {
            val tryLayout = LinearLayoutManager(context)
            tryLayout.reverseLayout = true
            tryLayout.stackFromEnd = true
            this.layoutManager = tryLayout
            this.adapter = dbHistoryItemAdapter
            this.setHasFixedSize(true)
        }

    }

    private fun insertHistoryItem(historyItem: DBHistoryItem) {
        lifecycleScope.launch(Dispatchers.IO) {
            (requireContext() as MainActivity).repository.insert(dbHistoryItem = historyItem)
        }
    }

    private fun retrieveDBHistoryItems() {
        lifecycleScope.launch(Dispatchers.IO) {
            val dbHistoryItems = (requireContext() as MainActivity).repository.getAllHistoryItems()
            withContext(Dispatchers.Main) {
                dbHistoryItemAdapter.setDBHistoryItems(dbHistoryItems)
            }
        }
    }

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
        buttonItIs.isClickable = state
        buttonItIs.isEnabled = state
        buttonItLess.isClickable = state
        buttonItLess.isEnabled = state
        buttonItBigger.isClickable = state
        buttonItBigger.isEnabled = state
    }

    private fun checkAttemptCount(count: Int) {
        if (count >= 21) {
            gameFragmentTopText.text = toMuchAttempts(count)
            changeStateButtonsExceptNewGame()
        }
    }

    private fun recyclerScrollToTop() {
        (recyclerViewGameFragment.layoutManager as LinearLayoutManager).scrollToPositionWithOffset(
            dbHistoryItemAdapter.itemCount - 1,
            0
        )
    }
}