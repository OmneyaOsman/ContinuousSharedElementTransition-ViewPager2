package com.omni.continuoussharedelementtransition_viewpager2.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.core.app.SharedElementCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import androidx.transition.Fade
import androidx.transition.TransitionSet
import com.omni.continuoussharedelementtransition_viewpager2.DataGenerator
import com.omni.continuoussharedelementtransition_viewpager2.R
import com.omni.continuoussharedelementtransition_viewpager2.SharedViewModel
import com.omni.continuoussharedelementtransition_viewpager2.waitForTransition

class GridFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var recyclerView: RecyclerView
    private val viewModel by activityViewModels<SharedViewModel>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.grid_fragment, container, false)
        recyclerView = rootView.findViewById(R.id.grid_recycler_view)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(recyclerView) {
            layoutManager = StaggeredGridLayoutManager( 2 ,LinearLayout.VERTICAL)
            adapter = GridAdapter().apply {
                submitList(DataGenerator.list)
            }
            exitTransition = exitTransition()
            setExitSharedElementCallback(sharedElementCallback)
            waitForTransition(this)

        }
    }

    /**  It's important to note that this callback will be called while exiting the Fragment
     * when the fragment-transaction occurs, and while re-entering the Fragment when it's popped out
     * of the backstack (on back navigation). We will use this behavior to remap
     *  the shared view and adjust the transition to handle cases where the view is changed
     *  after paging the images.*/

    private fun exitTransition() = TransitionSet()
        .addTransition(Fade().addTarget(R.id.card_view))
        .apply {
            duration = 375
            startDelay = 25
            interpolator = FastOutSlowInInterpolator()
        }


    private val sharedElementCallback = object : SharedElementCallback() {
        override fun onMapSharedElements(
            names: MutableList<String>,
            sharedElements: MutableMap<String, View>
        ) {


            viewModel.currentPositionLiveData.observe(viewLifecycleOwner, Observer { current ->

                with(recyclerView) {

                    recyclerView.findViewHolderForAdapterPosition(current) //  Map the first shared element name to the child ImageView.
                        ?.let {
                            sharedElements.put(
                                names[0],
                                it.itemView.findViewById(R.id.grid_image_view)
                            )
                        }
                    post {
                        layoutManager?.scrollToPosition(current)
                    }
                }
            })

        }
    }
}