package ru.mytest.onlybtsfuns.dataClasses

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class TextQuestion(val question: String, val  options: Array<String>, val answerNum: Int): Parcelable