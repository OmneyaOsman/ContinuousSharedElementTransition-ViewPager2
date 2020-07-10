package com.omni.continuoussharedelementtransition_viewpager2.pager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.app.SharedElementCallback
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.viewpager2.widget.ViewPager2
import com.omni.continuoussharedelementtransition_viewpager2.R
import com.omni.continuoussharedelementtransition_viewpager2.SharedViewModel
import com.omni.continuoussharedelementtransition_viewpager2.enterTransition

class ImagePagerFragment : Fragment() {

    private val args: ImagePagerFragmentArgs by navArgs()
    private lateinit var pager: ViewPager2
    private val viewModel by activityViewModels<SharedViewModel>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = enterTransition()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.image_pager_fragment, container, false)
        pager = rootView.findViewById(R.id.image_pager)
        val imagePosition = args.imgId - 1
        postponeEnterTransition()

        with(pager) {
            adapter = ImagePagerAdapter { position ->
                if (position == imagePosition) {
                    startPostponedEnterTransition()
                }
            }
            doOnPreDraw {
                setCurrentItem(imagePosition, false)
            }
            registerOnPageChangeCallback(pageCallback)
            setEnterSharedElementCallback(sharedElementCallback)
        }
        return rootView
    }

    private val pageCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            viewModel.updateCurrentPosition(position)
        }
    }
    private val sharedElementCallback = object : SharedElementCallback() {
        override fun onMapSharedElements(
            names: MutableList<String>,
            sharedElements: MutableMap<String, View>
        ) {
            viewModel.currentPositionLiveData.observe(viewLifecycleOwner, Observer { current ->
                pager.findViewWithTag<ImageView>(current)
                    ?.findViewById<ImageView>(R.id.pager_imageView_item)
                    ?.let { sharedElements[names[0]] = it }
            })
        }
    }


    override fun onDestroyView() {
        super.onDestroyView()
        pager.unregisterOnPageChangeCallback(pageCallback)
    }
}