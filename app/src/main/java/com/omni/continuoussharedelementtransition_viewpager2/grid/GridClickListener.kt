package com.omni.continuoussharedelementtransition_viewpager2.feature

import android.widget.ImageView

interface GridClickListener {
     val onClick: (Int, ImageView) -> Unit ?
}