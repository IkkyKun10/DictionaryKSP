package com.riezki.dictionaryksp.data.remote.model

import kotlinx.serialization.Serializable


/**
 * @author riezky maisyar
 */

@Serializable
data class WordResultDto(
    val words: List<WordItemDto>
)