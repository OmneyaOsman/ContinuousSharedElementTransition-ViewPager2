package com.omni.continuoussharedelementtransition_viewpager2.feature.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.ChangeClipBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import androidx.viewpager2.widget.ViewPager2
import com.omni.continuoussharedelementtransition_viewpager2.R

class ImagePagerFragment : Fragment() {

    private val args: ImagePagerFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedElementEnterTransition = setSharedImageTransition()

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.image_pager_fragment, container, false)
        val imagePosition = args.imgId - 1
        postponeEnterTransition()

        with(rootView.findViewById<ViewPager2>(R.id.image_pager))
        {
            adapter = ImagePagerAdapter { position ->
                if (position == imagePosition) {
                    startPostponedEnterTransition()
                }
            }

            doOnPreDraw {
                setCurrentItem(imagePosition, false)
            }
        }
        return rootView
    }

    private fun setSharedImageTransition() = TransitionSet()
        .addTransition(ChangeClipBounds())
        .addTransition(ChangeTransform())
        .addTransition(ChangeBounds())
        .apply {
            duration = 500
            ordering = TransitionSet.ORDERING_TOGETHER
            interpolator = FastOutSlowInInterpolator()
        }
}