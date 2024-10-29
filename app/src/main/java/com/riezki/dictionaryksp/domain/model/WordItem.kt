package com.riezki.dictionaryksp.domain.model

data class WordItem(
    val meanings: List<Meaning>? = null,
    val phonetic: String? = null,
    val word: String? = null
)
