package com.riezki.dictionaryksp.data.remote.api

import com.riezki.dictionaryksp.data.remote.model.WordResultDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.http.path

/**
 * @author riezky maisyar
 */

class KtorApi(private val client: HttpClient) {

    suspend fun getWord(word: String): WordResultDto {
        return client.get {
            url {
                path(word)
            }
        }.body()
    }

    companion object {
        const val BASE_URL = "api.dictionaryapi.dev/api/v2/entries/en"
    }
}