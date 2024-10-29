package com.riezki.dictionaryksp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WordItemDto(
    @SerialName("meanings")
    val meanings: List<MeaningDto?>? = null,
    @SerialName("phonetic")
    val phonetic: String? = null,
    @SerialName("word")
    val word: String? = null
)