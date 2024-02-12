package com.quantumcode.napets.ui.utils

import android.graphics.drawable.Drawable
import android.view.View
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.core.content.ContextCompat
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.google.android.material.button.MaterialButton
import com.quantumcode.napets.R

fun MaterialButton.setShowProgress(
    showProgress: Boolean?,
    textSource: String?
) {
    iconGravity = MaterialButton.ICON_GRAVITY_TEXT_START
    isClickable = showProgress == false
    text = if (showProgress == true) "" else textSource
    icon = if (showProgress == true){
        CircularProgressDrawable(context).apply {
            setStyle(CircularProgressDrawable.LARGE)
            setColorSchemeColors(
                ContextCompat.getColor(
                    context, R.color.white))
            start()
        }
    } else {
        getDrawable(context, R.drawable.ic_round_check_circle).apply {
            textAlignment = View.TEXT_ALIGNMENT_CENTER
        }
    }
    icon?.let {
        icon.callback = object : Drawable.Callback{
            override fun invalidateDrawable(who: Drawable) {
                this@setShowProgress.invalidate()
            }

            override fun scheduleDrawable(who: Drawable, what: Runnable, `when`: Long) {

            }

            override fun unscheduleDrawable(who: Drawable, what: Runnable) {

            }

        }
    }
}

fun MaterialButton.removeProgress() {
    icon = null
}