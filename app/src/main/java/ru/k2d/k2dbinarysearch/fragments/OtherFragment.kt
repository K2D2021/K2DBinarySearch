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


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

class OtherFragment : Fragment() {
    private var param1: String? = null
    private var param2: String? = null
    private lateinit var imageV: ImageView
    private lateinit var other_fragment: FrameLayout

    //lateinit var bitmap: Bitmap
//    imageV = findViewById(R.id.imageV)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }


    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_other, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        imageV = view.findViewById(R.id.imageV)
        val animation = AnimationUtils.loadAnimation(requireContext(), R.anim.round_and_round)
        imageV.startAnimation(animation)
        other_fragment = view.findViewById(R.id.other_frame_background)
        val AnimationDrawable: AnimationDrawable = other_fragment.background as AnimationDrawable
        AnimationDrawable.setEnterFadeDuration(1500)
        AnimationDrawable.setExitFadeDuration(3000)
        AnimationDrawable.start()
        //imageV.background = BitmapDrawable(getResources(), bitmap)*/
        super.onViewCreated(view, savedInstanceState)
    }


}