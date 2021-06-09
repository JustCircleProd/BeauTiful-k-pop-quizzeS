package ru.mytest.onlybtsfuns.dataClasses

import android.os.Parcelable
import androidx.annotation.DrawableRes
import kotlinx.parcelize.Parcelize

@Parcelize
data class ImageQuestion(
    @DrawableRes val image: Int,
    val options: Array<String>,
    val answerNum: Int
) :
    Parcelable