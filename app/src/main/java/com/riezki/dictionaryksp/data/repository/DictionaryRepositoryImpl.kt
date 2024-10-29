package com.riezki.dictionaryksp.data.repository

import com.riezki.dictionaryksp.data.remote.model.WordItemDto
import com.riezki.dictionaryksp.domain.repository.DictionaryRepository
import com.riezki.dictionaryksp.utils.Resource
import kotlinx.coroutines.flow.Flow

/**
 * @author riezky maisyar
 */

class DictionaryRepositoryImpl : DictionaryRepository {
    override fun getWord(word: String): Flow<Resource<WordItemDto>> {
        TODO("Not yet implemented")
    }
}