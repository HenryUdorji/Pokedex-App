
package com.henryudorji.pokedex.utils

import android.content.Context
import android.view.View
import android.widget.TextView
import androidx.annotation.DrawableRes
import androidx.appcompat.widget.SearchView
import androidx.core.content.ContextCompat

fun View.show() {
    visibility = View.VISIBLE
}

fun View.hide() {
    visibility = View.GONE
}

fun View.setVisible(isVisible: Boolean) {
    if (isVisible) show() else hide()
}

fun TextView.setDrawableLeft(@DrawableRes id: Int = 0) {
    this.setCompoundDrawablesWithIntrinsicBounds(id, 0, 0, 0)
}

fun Context.getColorCompat(color: Int) = ContextCompat.getColor(this, color)

inline fun SearchView.OnQueryTextChanged(crossinline listener: (String) -> Unit) {
    this.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String?): Boolean {
            listener(newText.orEmpty())
            return true
        }

    })
}
