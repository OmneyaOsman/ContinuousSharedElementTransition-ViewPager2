package com.omni.continuoussharedelementtransition_viewpager2.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.omni.continuoussharedelementtransition_viewpager2.R
import kotlinx.android.synthetic.main.pager_image_list_item.view.*

class ImagePagerFragment : Fragment() {

    private val args: ImagePagerFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.slide_top)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.pager_image_list_item, container, false)

        rootView.pager_imageView_item
            .let {
                Glide.with(it.context)
                    .load(DataGenerator.list[args.imgPosition].imageResource)
                    .apply(
                        RequestOptions()
                            .placeholder(R.drawable.loading_animation)
                            .error(R.drawable.ic_broken_image)
                    )
                    .into(it)
            }
        return rootView
    }
}