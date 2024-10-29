package com.riezki.dictionaryksp.data.mapper

import com.riezki.dictionaryksp.data.remote.model.DefinitionDto
import com.riezki.dictionaryksp.data.remote.model.MeaningDto
import com.riezki.dictionaryksp.data.remote.model.WordItemDto
import com.riezki.dictionaryksp.domain.model.Definition
import com.riezki.dictionaryksp.domain.model.Meaning
import com.riezki.dictionaryksp.domain.model.WordItem

/**
 * @author riezky maisyar
 */

fun WordItemDto.toWordItem() : WordItem {
    return WordItem(
        meanings = meanings?.map {
            it?.toMeaning() ?: Meaning()
        } ?: emptyList(),
        word = word,
        phonetic = phonetic
    )
}

fun MeaningDto.toMeaning() : Meaning {
    return Meaning(
        definition = definitionDtoToDefinition(definitions?.get(0)),
        partOfSpeech = partOfSpeech
    )
}

fun definitionDtoToDefinition(
    definitionDto: DefinitionDto?
) : Definition {
    return Definition(
        definition = definitionDto?.definition,
        example = definitionDto?.example
    )
}