package ru.k2d.k2dbinarysearch.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.fragment.app.Fragment
import ru.k2d.k2dbinarysearch.R
import ru.k2d.k2dbinarysearch.databinding.FragmentAboutAuthorsBinding

class AboutAuthors : Fragment() {

    private var binding: FragmentAboutAuthorsBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentAboutAuthorsBinding.inflate(inflater, container, false)
        return binding!!.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val animationNewGameIconMoveLeftToRight = AnimationUtils.loadAnimation(requireContext(), R.anim.new_game_icon_animation_move_left_to_right)
        binding?.animatedNewGameIcon?.startAnimation(animationNewGameIconMoveLeftToRight)


        val backgroundAnimation: AnimationDrawable = binding?.root?.background as AnimationDrawable
        backgroundAnimation.setEnterFadeDuration(1500)
        backgroundAnimation.setExitFadeDuration(3000)
        backgroundAnimation.start()
        super.onViewCreated(view, savedInstanceState)
    }


}