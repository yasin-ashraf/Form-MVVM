package com.yasin.handzap.utils

import android.content.Context
import android.util.AttributeSet
import android.view.Gravity
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.TextView
import com.google.android.material.textfield.TextInputLayout

/**
 * Created by Yasin on 25/1/20.
 */
class RightAlignTextInputLayout(context: Context, attributeSet: AttributeSet) : TextInputLayout(context,attributeSet) {

    override fun setErrorEnabled(enabled: Boolean) {
        super.setErrorEnabled(enabled)
        if (!enabled) {
            return
        }
        try {
            val layout = this
            val errorView : TextView = ((this.getChildAt(1) as ViewGroup).getChildAt(0) as ViewGroup).getChildAt(0) as TextView

            (layout.getChildAt(1) as ViewGroup).layoutParams.width = LayoutParams.MATCH_PARENT
            (layout.getChildAt(1) as ViewGroup).layoutParams.height = LayoutParams.MATCH_PARENT
            (layout.getChildAt(1) as ViewGroup).getChildAt(0).layoutParams.width = FrameLayout.LayoutParams.MATCH_PARENT

            errorView.gravity = Gravity.END

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getMinimumWidth(): Int {
        return super.getMinimumWidth()
    }

}