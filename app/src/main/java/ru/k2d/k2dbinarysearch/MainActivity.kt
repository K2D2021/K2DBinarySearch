package ru.k2d.k2dbinarysearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.activity_main.*
import ru.k2d.k2dbinarysearch.data.AppDatabase
import ru.k2d.k2dbinarysearch.data.DBHistoryItemRepositoryImpl
import ru.k2d.k2dbinarysearch.data.HistoryItemRepository
import ru.k2d.k2dbinarysearch.fragments.GameFragment
import ru.k2d.k2dbinarysearch.fragments.HistoryFragment
import ru.k2d.k2dbinarysearch.fragments.HomeFragment
import ru.k2d.k2dbinarysearch.fragments.OtherFragment


class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase

    lateinit var repository: HistoryItemRepository

    private var myFragmentsStack = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        database = AppDatabase.buildDatabase(applicationContext, DATABASE_NAME)

        repository = DBHistoryItemRepositoryImpl(database.historyItemDAO())


        setContentView(R.layout.activity_main)
        bottom_navigation.itemIconTintList = null

        replaceFragment(HomeFragment())
        bottom_navigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home_icon -> replaceFragment(HomeFragment())
                R.id.ic_game_icon -> replaceFragment(GameFragment())
                R.id.ic_history_icon -> replaceFragment(HistoryFragment())
                R.id.ic_other_icon -> replaceFragment(OtherFragment())
            }
            true
        }
        bottom_navigation.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.ic_home_icon -> replaceFragment(HomeFragment())
                R.id.ic_game_icon -> replaceFragment(GameFragment())
                R.id.ic_history_icon -> replaceFragment(HistoryFragment())
                R.id.ic_other_icon -> replaceFragment(OtherFragment())
            }
        }
    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.addToBackStack(fragment.toString().substringBefore("Fragment"))
        transaction.commit()
        myFragmentsStack += fragment.toString().substringBefore("Fragment")
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {
        val manager: FragmentManager = supportFragmentManager
        if (manager.backStackEntryCount > 1) {
            manager.popBackStack()
        } else {
            moveTaskToBack(true)
        }
        val f = manager.getBackStackEntryAt(manager.backStackEntryCount - 1).name
        myFragmentsStack.remove(f)
        paintSelectedTabOnBackPressed()
    }

    /*fun newWayToChangeFragment() {
        bottom_navigation.menu.findItem(R.id.ic_game_icon).isChecked = true
    }*/
    fun newWayToChangeFragment(fragment: Fragment) {
        when(fragment.toString().substringBefore("Fragment")){
            "Game" -> bottom_navigation.menu.findItem(R.id.ic_game_icon).isChecked = true
            "History" -> bottom_navigation.menu.findItem(R.id.ic_history_icon).isChecked = true
            else -> Toast.makeText(
                this,
                "${myFragmentsStack[myFragmentsStack.size]} something wrong",
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    private fun paintSelectedTabOnBackPressed() {
        if (myFragmentsStack.size != 0) {
            when (myFragmentsStack[myFragmentsStack.size - 1]) {
                "History" -> bottom_navigation.menu.findItem(R.id.ic_history_icon).isChecked = true
                "Game" -> bottom_navigation.menu.findItem(R.id.ic_game_icon).isChecked = true
                "Home" -> bottom_navigation.menu.findItem(R.id.ic_home_icon).isChecked = true
                "Other" -> bottom_navigation.menu.findItem(R.id.ic_other_icon).isChecked = true
                else -> Toast.makeText(
                    this,
                    "${myFragmentsStack[myFragmentsStack.size]} something wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    companion object{
        private const val DATABASE_NAME = "k2d_bs_database.db"
    }
}

