package ru.k2d.k2dbinarysearch

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.k2d.k2dbinarysearch.fragments.GameFragment
import ru.k2d.k2dbinarysearch.fragments.HistoryFragment
import ru.k2d.k2dbinarysearch.fragments.HomeFragment
import ru.k2d.k2dbinarysearch.fragments.OtherFragment

class MainActivity : AppCompatActivity() {
    private var doubleBackToExitPressedOnce = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
        transaction.addToBackStack("TAG")
        transaction.commit()
        val count = supportFragmentManager.backStackEntryCount
            Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()

    }
    /*override fun onBackPressed() {
         if(BackS)
         if (doubleBackToExitPressedOnce) {
             super.onBackPressed()
             return
         }

         this.doubleBackToExitPressedOnce = true
         Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()

         //Handler(Looper.getMainLooper()).postDelayed(Runnable { doubleBackToExitPressedOnce = false }, 2000)
     }*/

    override fun onBackPressed() {
        val count = supportFragmentManager.backStackEntryCount
        Toast.makeText(this, count.toString(), Toast.LENGTH_SHORT).show()
        if (count == 2) {
            if (doubleBackToExitPressedOnce) {
                super.onBackPressed()
                return
            }
        } else {
            //Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
            super.onBackPressed()
        }
        this.doubleBackToExitPressedOnce = true
    }
}

