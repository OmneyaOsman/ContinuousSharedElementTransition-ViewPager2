package com.omni.continuoussharedelementtransition_viewpager2.feature.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.fragment.app.Fragment
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.navigation.fragment.navArgs
import androidx.transition.ChangeBounds
import androidx.transition.ChangeClipBounds
import androidx.transition.ChangeTransform
import androidx.transition.TransitionSet
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
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
        val rootView = inflater.inflate(R.layout.pager_image_list_item, container, false)

        rootView.findViewById<ImageView>(R.id.pager_imageView_item).run {
            transitionName = "${resources.getString(R.string.transition_shared_img)}_${args.imgId}"

            Glide.with(requireContext())
                .load(args.imgResource)
                .apply(

                    RequestOptions().dontTransform()
                        .placeholder(R.drawable.loading_animation)
                        .error(R.drawable.ic_broken_image)
                )
                .into(this)
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