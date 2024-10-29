package com.riezki.dictionaryksp.data.remote.model


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DefinitionDto(
    @SerialName("definition")
    val definition: String? = null,
    @SerialName("example")
    val example: String? = null,
)