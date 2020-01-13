package com.omni.continuoussharedelementtransition_viewpager2.feature

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.omni.continuoussharedelementtransition_viewpager2.R

class ImagePagerFragment :Fragment(){
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.image_pager_fragment, container , false)
    }
}