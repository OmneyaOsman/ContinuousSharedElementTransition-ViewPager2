package com.omni.continuoussharedelementtransition_viewpager2.feature.grid

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omni.continuoussharedelementtransition_viewpager2.R
import com.omni.continuoussharedelementtransition_viewpager2.feature.DataGenerator
import com.omni.continuoussharedelementtransition_viewpager2.feature.GridAdapter
import com.omni.continuoussharedelementtransition_viewpager2.feature.waitForTransition
import kotlinx.android.synthetic.main.grid_fragment.view.*

class GridFragment : Fragment() {

    private lateinit var rootView: View
    private lateinit var gridAdapter: GridAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        rootView = inflater.inflate(R.layout.grid_fragment, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(rootView.grid_recycler_view) {
            gridAdapter = GridAdapter()
            adapter = gridAdapter
            gridAdapter.submitList(DataGenerator.list)
            waitForTransition(this)

        }

    }
}