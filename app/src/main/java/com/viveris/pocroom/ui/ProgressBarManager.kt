package com.viveris.pocroom.ui

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.animation.AlphaAnimation
import android.view.inputmethod.InputMethodManager
import android.widget.FrameLayout


/**
 * This class manage the display of a loading overlay
 */
class ProgressBarManager {

    /**
     * display/hide a loading overlay
     * @param isLoading to know if we display or hide the frame layout
     * @param progressBarHolder the pregress bar id
     */
    fun onLoaderStateChange(isLoading: Boolean, progressBarHolder: FrameLayout?) {
        progressBarHolder?.apply {
            hideKeyboardFrom(progressBarHolder.context, progressBarHolder.rootView)
            val animation: AlphaAnimation = when (isLoading) {
                true -> AlphaAnimation(0f, 1f)
                else -> AlphaAnimation(1f, 0f)
            }
            animation.duration = 200
            this.animation = animation
            visibility = when (isLoading) {
                true -> View.VISIBLE
                else -> View.GONE
            }
        }
    }

    private fun hideKeyboardFrom(context: Context, view: View) {
        val imm: InputMethodManager = context.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(view.windowToken, 0)
    }
}