package com.sina.notepadfinal.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.text.Editable
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import java.text.SimpleDateFormat

fun Fragment.hideKeyboard() {
    view?.let { activity?.hideKeyboard(it) }
}

fun Activity.hideKeyboard() {
    hideKeyboard(currentFocus ?: View(this))
}

fun Context.hideKeyboard(view: View) {
    val inputMethodManager = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
    inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
}
@SuppressLint("SimpleDateFormat")
fun Long.reformat(): String {
    val df=SimpleDateFormat("(yyyy/MM/dd - HH:mm)")
    return df.format(this)
}
fun String.toEditable(): Editable =  Editable.Factory.getInstance().newEditable(this)