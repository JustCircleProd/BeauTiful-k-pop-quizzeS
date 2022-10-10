package com.justcircleprod.beautifulkpopquizzes.ui.extensions

import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.widget.TextView
import androidx.core.content.ContextCompat
import com.justcircleprod.beautifulkpopquizzes.R

fun TextView.makeBrandLabelColorful() {
    this.text = SpannableString(context.getString(R.string.app_name)).apply {
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.accent_brand_color
                )
            ), 0, 1,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.accent_brand_color
                )
            ), 4, 5,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
        setSpan(
            ForegroundColorSpan(
                ContextCompat.getColor(
                    context,
                    R.color.accent_brand_color
                )
            ), 22, 23,
            Spannable.SPAN_EXCLUSIVE_EXCLUSIVE
        )
    }
}