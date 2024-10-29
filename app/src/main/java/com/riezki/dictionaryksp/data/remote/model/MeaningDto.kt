package com.riezki.dictionaryksp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class MeaningDto(
    @SerialName("definitions")
    val definitions: List<DefinitionDto?>? = null,
    @SerialName("partOfSpeech")
    val partOfSpeech: String? = null
)