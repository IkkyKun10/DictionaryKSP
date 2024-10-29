package com.riezki.dictionaryksp.data.repository

import com.riezki.dictionaryksp.data.mapper.toWordItem
import com.riezki.dictionaryksp.data.remote.api.KtorApi
import com.riezki.dictionaryksp.domain.model.WordItem
import com.riezki.dictionaryksp.domain.repository.DictionaryRepository
import com.riezki.dictionaryksp.utils.ErrorType
import com.riezki.dictionaryksp.utils.Resource
import io.ktor.client.network.sockets.SocketTimeoutException
import io.ktor.client.plugins.ClientRequestException
import io.ktor.client.plugins.HttpRequestTimeoutException
import io.ktor.client.plugins.ResponseException
import io.ktor.client.plugins.ServerResponseException
import io.ktor.utils.io.errors.IOException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.serialization.SerializationException
import javax.inject.Inject

/**
 * @author riezky maisyar
 */

class DictionaryRepositoryImpl @Inject constructor(
    private val api: KtorApi
) : DictionaryRepository {
    override fun getWord(word: String): Flow<Resource<WordItem>> {
        return flow {
            emit(Resource.Loading(null))

            val remoteResponseDto = try {
                api.getWord(word)
            } catch (e: ClientRequestException) {
                emit(Resource.Error(ErrorType.CLIENT_EXCEPTION, e.message, null))
                return@flow
            } catch (e: ServerResponseException) {
                emit(Resource.Error(ErrorType.SERVER_EXCEPTION, e.message, null))
                return@flow
            } catch (e: HttpRequestTimeoutException) {
                emit(Resource.Error(ErrorType.TIMEOUT_EXCEPTION, e.message.toString(), null))
                return@flow
            } catch (e: SocketTimeoutException) {
                emit(Resource.Error(ErrorType.TIMEOUT_EXCEPTION, e.message.toString(), null))
                return@flow
            } catch (e: IOException) {
                emit(Resource.Error(ErrorType.IO_EXCEPTION, e.message.toString(), null))
                return@flow
            } catch (e: SerializationException) {
                emit(Resource.Error(ErrorType.SERIALIZATION_EXCEPTION, e.message.toString(), null))
                return@flow
            } catch (e: ResponseException) {
                emit(Resource.Error(ErrorType.HTTP_EXCEPTION, e.message.toString(), null))
                return@flow
            } catch (e: Exception) {
                emit(Resource.Error(ErrorType.UNKNOWN_EXCEPTION, e.message.toString(), null))
                return@flow
            }

            remoteResponseDto?.let { wordResultDto ->
                wordResultDto[0].let { wordItemDto ->
                    emit(Resource.Success(wordItemDto.toWordItem()))
                    return@flow
                }
            }

            emit(Resource.Error(ErrorType.UNKNOWN_EXCEPTION, "Unknown Error", null))
        }
    }
}