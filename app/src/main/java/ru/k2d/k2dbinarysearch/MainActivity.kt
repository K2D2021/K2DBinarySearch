package ru.k2d.k2dbinarysearch

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.lifecycleScope
import ru.k2d.k2dbinarysearch.api.ApiInterface
import ru.k2d.k2dbinarysearch.api.RetrofitClient
import ru.k2d.k2dbinarysearch.data.AppDatabase
import ru.k2d.k2dbinarysearch.data.DBHistoryItemRepositoryImpl
import ru.k2d.k2dbinarysearch.data.HistoryItemRepository
import ru.k2d.k2dbinarysearch.databinding.ActivityMainBinding
import ru.k2d.k2dbinarysearch.fragments.GameFragment
import ru.k2d.k2dbinarysearch.fragments.HistoryFragment
import ru.k2d.k2dbinarysearch.fragments.HomeFragment
import ru.k2d.k2dbinarysearch.fragments.AboutAuthors
import java.security.SecureRandom


class MainActivity : AppCompatActivity() {

    private lateinit var database: AppDatabase
    private lateinit var binding: ActivityMainBinding
    lateinit var repository: HistoryItemRepository

    private var myFragmentsStack = arrayListOf<String>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        database = AppDatabase.buildDatabase(applicationContext, DATABASE_NAME)

        repository = DBHistoryItemRepositoryImpl(database.historyItemDAO())

        setContentView(view)
        binding.bottomNavigation.itemIconTintList = null

        replaceFragment(HomeFragment())
        binding.bottomNavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.ic_home_icon -> replaceFragment(HomeFragment())
                R.id.ic_game_icon -> replaceFragment(GameFragment())
                R.id.ic_history_icon -> replaceFragment(HistoryFragment())
                R.id.ic_about_authors_icon -> replaceFragment(AboutAuthors())
            }
            true
        }
        binding.bottomNavigation.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.ic_home_icon -> replaceFragment(HomeFragment())
                R.id.ic_game_icon -> replaceFragment(GameFragment())
                R.id.ic_history_icon -> replaceFragment(HistoryFragment())
                R.id.ic_about_authors_icon -> replaceFragment(AboutAuthors())
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
            "Game" -> binding.bottomNavigation.menu.findItem(R.id.ic_game_icon).isChecked = true
            "History" -> binding.bottomNavigation.menu.findItem(R.id.ic_history_icon).isChecked = true
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
                "History" -> binding.bottomNavigation.menu.findItem(R.id.ic_history_icon).isChecked = true
                "Game" -> binding.bottomNavigation.menu.findItem(R.id.ic_game_icon).isChecked = true
                "Home" -> binding.bottomNavigation.menu.findItem(R.id.ic_home_icon).isChecked = true
                "Other" -> binding.bottomNavigation.menu.findItem(R.id.ic_about_authors_icon).isChecked = true
                else -> Toast.makeText(
                    this,
                    "${myFragmentsStack[myFragmentsStack.size]} something wrong",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    fun getFirstRandom(upperLimit: Int = 100000) = SecureRandom().nextInt(upperLimit)

    companion object {
        private const val DATABASE_NAME = "k2d_bs_database.db"
    }

    private fun convertRandomLongToInt(inputRandomNumber: Long): Int {
        if (inputRandomNumber > 100000) return inputRandomNumber.toString().substring(0, 5).toInt()
        return inputRandomNumber.toInt()
    }


    fun introductionTextWithRetrofit(textHomeFragment: TextView) {
        val retrofit = RetrofitClient.getInstance()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.getRandomNumberRetro()
                if (response.isSuccessful) {

                    firstTextPlusRandom(convertRandomLongToInt(response.body()?.number!!), textHomeFragment)
                } else {
                    firstTextPlusRandom(getFirstRandom(), textHomeFragment)
                }
            } catch (Ex: Exception) {
                    firstTextPlusRandom(getFirstRandom(), textHomeFragment)
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun firstTextPlusRandom(inputRandomNumber: Int, textHomeFragment: TextView){
        textHomeFragment.text = getString(R.string.first_text) + " " + inputRandomNumber.toString()
    }

}
