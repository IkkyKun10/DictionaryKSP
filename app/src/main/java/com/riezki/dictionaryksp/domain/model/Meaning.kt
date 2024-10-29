package com.riezki.dictionaryksp.domain.model

data class Meaning(
    val definitions: List<Definition>? = null,
    val partOfSpeech: String? = null
)
