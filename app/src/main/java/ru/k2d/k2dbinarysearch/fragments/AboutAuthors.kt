package ru.k2d.k2dbinarysearch.fragments

import android.graphics.drawable.AnimationDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.fragment.app.Fragment
import ru.k2d.k2dbinarysearch.R

class AboutAuthors : Fragment() {
    private lateinit var animatedNewGameIcon: ImageView
    private lateinit var aboutAuthorsFragment: FrameLayout

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_about_authors, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        animatedNewGameIcon = view.findViewById(R.id.animatedNewGameIcon)
        val animationNewGameIconMoveLeftToRight = AnimationUtils.loadAnimation(requireContext(), R.anim.new_game_icon_animation_move_left_to_right)
        animatedNewGameIcon.startAnimation(animationNewGameIconMoveLeftToRight)


        aboutAuthorsFragment = view.findViewById(R.id.about_authors_frame_background)
        val backgroundAnimation: AnimationDrawable = aboutAuthorsFragment.background as AnimationDrawable
        backgroundAnimation.setEnterFadeDuration(1500)
        backgroundAnimation.setExitFadeDuration(3000)
        backgroundAnimation.start()
        super.onViewCreated(view, savedInstanceState)
    }


}