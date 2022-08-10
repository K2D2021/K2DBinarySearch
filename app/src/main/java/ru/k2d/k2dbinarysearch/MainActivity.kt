package ru.k2d.k2dbinarysearch

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_game.*
import ru.k2d.k2dbinarysearch.api.ApiInterface
import ru.k2d.k2dbinarysearch.api.RetrofitClient
import ru.k2d.k2dbinarysearch.data.AppDatabase
import ru.k2d.k2dbinarysearch.data.DBHistoryItemRepositoryImpl
import ru.k2d.k2dbinarysearch.data.HistoryItemRepository
import ru.k2d.k2dbinarysearch.fragments.GameFragment
import ru.k2d.k2dbinarysearch.fragments.HistoryFragment
import ru.k2d.k2dbinarysearch.fragments.HomeFragment
import ru.k2d.k2dbinarysearch.fragments.OtherFragment
import ru.k2d.k2dbinarysearch.models.retrofitRandomNumber
import java.security.SecureRandom
import kotlin.concurrent.thread


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

    fun newWayToChangeFragment(fragment: Fragment) {
        when (fragment.toString().substringBefore("Fragment")) {
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

    fun getFirstRandom(upperLimit: Int) = SecureRandom().nextInt(upperLimit)

    companion object {
        private const val DATABASE_NAME = "k2d_bs_database.db"
    }

    private fun convertRandomLongToInt(inputRandomNumber: Long): Int {
        if (inputRandomNumber > 100000) return inputRandomNumber.toString().substring(0, 5).toInt()
        return inputRandomNumber.toInt()
    }



    fun generateRandomNumberViaRetrofit(upperLimit: Int): Int {

        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)
        var randomNumber = 777

        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getRandomNumberRetro()
                if (response.isSuccessful) {
                    //txtData.text = response.body()?.joke.toString()
//                    Toast.makeText(this@MainActivity, response.body()?.number.toString(), Toast.LENGTH_SHORT).show()
                   /* randomNumber = response.body()?.let { convertRandomLongToInt(it.number) }
                        ?: getFirstRandom(upperLimit)*/
                    /*Toast.makeText(this@MainActivity, randomNumber.toString(), Toast.LENGTH_SHORT)
                        .show()*/
                    guestextF.text = convertRandomLongToInt(response.body()?.number!!).toString()
                } else {
                    randomNumber = getFirstRandom(upperLimit)
                }
            } catch (Ex: Exception) {
                Log.e("Error", Ex.localizedMessage)
            }
        }
        return randomNumber
    }

}

