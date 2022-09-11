package ru.justcircleprod.onlybtsfuns.util

import java.util.*

fun areEnglishResourcesUsed(): Boolean {
    val systemLanguage = Locale.getDefault().language

    return systemLanguage != "be" && systemLanguage != "hy" && systemLanguage != "kk" &&
            systemLanguage != "ky" && systemLanguage != "ru" && systemLanguage != "tk" &&
            systemLanguage != "uk" && systemLanguage != "uz"
}