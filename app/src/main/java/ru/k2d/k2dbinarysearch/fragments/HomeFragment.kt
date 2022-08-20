package ru.k2d.k2dbinarysearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import ru.k2d.k2dbinarysearch.MainActivity
import ru.k2d.k2dbinarysearch.R

class HomeFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonF.setOnClickListener {
            (activity as MainActivity).replaceFragment(GameFragment())
            (activity as MainActivity).newWayToChangeFragment(GameFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        (activity as MainActivity).introductionTextWithRetrofit(textView3)
    }
}
