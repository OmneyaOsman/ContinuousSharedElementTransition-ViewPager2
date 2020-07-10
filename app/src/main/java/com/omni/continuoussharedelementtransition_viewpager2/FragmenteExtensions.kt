package com.omni.continuoussharedelementtransition_viewpager2

import android.view.View
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import kotlinx.coroutines.suspendCancellableCoroutine
import kotlin.coroutines.resume

fun Fragment.waitForTransition(targetView: View) {
    postponeEnterTransition()
    targetView.doOnPreDraw { startPostponedEnterTransition() }
}

/**
 * Await an item in the data set with the given [itemId], and return its adapter position.
 */


suspend fun View.awaitAnimationFrame() = suspendCancellableCoroutine<Unit> { continuation ->
    val runnable = Runnable {
        continuation.resume(Unit)
    }
    // If the coroutine is cancelled, remove the callback
    continuation.invokeOnCancellation { removeCallbacks(runnable) }
    // And finally post the runnable
    postOnAnimation(runnable)
}




