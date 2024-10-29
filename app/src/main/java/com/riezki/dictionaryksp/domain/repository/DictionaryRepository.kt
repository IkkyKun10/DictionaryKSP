package com.riezki.dictionaryksp.domain.repository

import com.riezki.dictionaryksp.data.remote.model.WordItemDto
import com.riezki.dictionaryksp.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author riezky maisyar
 */

interface DictionaryRepository {
    fun getWord(word: String): Flow<Resource<WordItemDto>>
}