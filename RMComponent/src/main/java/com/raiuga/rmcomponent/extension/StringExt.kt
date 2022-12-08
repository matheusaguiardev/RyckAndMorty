package com.raiuga.rmcomponent.extension

import java.text.Normalizer

private val RegexWithoutAccent = "\\p{InCombiningDiacriticalMarks}+".toRegex()

internal fun CharSequence.removeAccent(): String {
    val temp = Normalizer.normalize(this, Normalizer.Form.NFD)
    return RegexWithoutAccent.replace(temp, "")
}