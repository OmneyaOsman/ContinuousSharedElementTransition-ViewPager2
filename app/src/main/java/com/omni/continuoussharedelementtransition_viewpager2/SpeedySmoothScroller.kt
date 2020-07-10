package com.omni.continuoussharedelementtransition_viewpager2

import android.content.Context
import android.util.DisplayMetrics
import androidx.recyclerview.widget.LinearSmoothScroller


class SpeedySmoothScroller
constructor(
    context: Context?, val snapPreference: Int, val scrollMsPerInch: Float
) : LinearSmoothScroller(context) {


    override fun calculateSpeedPerPixel(displayMetrics: DisplayMetrics): Float {
        return scrollMsPerInch / displayMetrics.densityDpi
    }
}


