package com.example.data.model

enum class AppLanguage {
    KANNADA,
    ENGLISH
}

data class LocalizedText(
    val kn: String,
    val en: String
) {
    fun get(lang: AppLanguage): String = if (lang == AppLanguage.KANNADA) kn else en
}
