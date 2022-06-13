package com.mtnine.dohwaji.util

import android.content.Context
import android.widget.Toast

object Utils {
    fun Context.toast(text: String) = Toast.makeText(this, text, Toast.LENGTH_SHORT).show()

    fun Context.toast(textId: Int) = Toast.makeText(this, textId, Toast.LENGTH_SHORT).show()
}