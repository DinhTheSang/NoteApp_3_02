package com.example.noteapp

import android.graphics.drawable.Drawable
import android.widget.Button
import android.widget.LinearLayout

interface DisplayColorNote {
    fun getLlDrawable(color: String): Drawable
    fun getBtDrawable(color: String): Drawable
}