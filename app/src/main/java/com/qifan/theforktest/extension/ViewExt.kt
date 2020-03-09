package com.qifan.theforktest.extension

import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText


fun ViewGroup.inflateLayout(layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)
}

inline fun <reified T : Any> checkNullOrHideView(
    view: View,
    parameter: T?,
    showFunc: (parameter: T) -> Unit
) {
    parameter?.run {
        showFunc(this)
        view.visibility = View.VISIBLE
    } ?: view.apply { visibility = View.GONE }
}


inline fun EditText.afterTextChanged(crossinline afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        }

        override fun afterTextChanged(editable: Editable?) {
            if (hasFocus()) {
                afterTextChanged.invoke(editable.toString())
            }
        }
    })
}