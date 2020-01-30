package com.omni.continuoussharedelementtransition_viewpager2

import androidx.annotation.DrawableRes

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
        ImageData(8, R.drawable.pic_eight),
        ImageData(9, R.drawable.pic_nine),
        ImageData(10, R.drawable.pic_ten),
        ImageData(11, R.drawable.pic_eleven),
        ImageData(12, R.drawable.pic_twelve),
        ImageData(13, R.drawable.pic_therteen),
        ImageData(14, R.drawable.pic_fourteen)
    )
}


