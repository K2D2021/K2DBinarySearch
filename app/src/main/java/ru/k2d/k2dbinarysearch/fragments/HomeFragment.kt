package ru.k2d.k2dbinarysearch.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import ru.k2d.k2dbinarysearch.MainActivity
import ru.k2d.k2dbinarysearch.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {

    private var binding: FragmentHomeBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding?.buttonLetsPlay?.setOnClickListener {
            (activity as MainActivity).replaceFragment(GameFragment())
            (activity as MainActivity).newWayToChangeFragment(GameFragment())
        }
    }

    override fun onResume() {
        super.onResume()
        binding?.let { (activity as MainActivity).introductionTextWithRetrofit(it.homeFragmentText) }
    }
}
