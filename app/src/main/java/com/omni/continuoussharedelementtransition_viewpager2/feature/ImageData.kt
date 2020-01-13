package com.omni.continuoussharedelementtransition_viewpager2.feature

import androidx.annotation.DrawableRes
import com.omni.continuoussharedelementtransition_viewpager2.R

data class ImageData(val id: Int, @DrawableRes val imageResource: Int)

object DataGenerator {
    val list = listOf(
        ImageData(1, R.drawable.pic_one),
        ImageData(2, R.drawable.pic_two),
        ImageData(3, R.drawable.pic_three),
        ImageData(4, R.drawable.pic_four),
        ImageData(5, R.drawable.pic_five),
        ImageData(6, R.drawable.pic_six),
        ImageData(7, R.drawable.pic_seven),
        ImageData(8, R.drawable.pic_eight)
    )

}


