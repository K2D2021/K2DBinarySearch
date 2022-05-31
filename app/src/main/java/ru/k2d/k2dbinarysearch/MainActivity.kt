package ru.k2d.k2dbinarysearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.activity_main.*
import ru.k2d.k2dbinarysearch.fragments.GameFragment
import ru.k2d.k2dbinarysearch.fragments.HistoryFragment
import ru.k2d.k2dbinarysearch.fragments.HomeFragment
import ru.k2d.k2dbinarysearch.fragments.OtherFragment

class MainActivity : AppCompatActivity() {
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

    }

    fun replaceFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }
}
