package com.omni.continuoussharedelementtransition_viewpager2

import androidx.interpolator.view.animation.FastOutSlowInInterpolator
import androidx.transition.*


fun exitTransition() = TransitionSet()
    .addTransition(Fade().addTarget(R.id.card_view))
    .apply {
        duration = 375
        startDelay = 25
        interpolator = FastOutSlowInInterpolator()
    }


fun enterTransition() = TransitionSet()
    .addTransition(ChangeClipBounds())
    .addTransition(ChangeTransform())
    .addTransition(ChangeBounds())
    .apply {
        duration = 500
        ordering = TransitionSet.ORDERING_TOGETHER
        interpolator = FastOutSlowInInterpolator()
    }