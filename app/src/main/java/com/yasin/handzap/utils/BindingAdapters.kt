package com.yasin.handzap.utils

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout


/**
 * Created by Yasin on 26/1/20.
 */

@BindingAdapter("app:errorText")
fun setErrorMessage(view: TextInputLayout, errorMessage: String?) {
    view.error = errorMessage
}