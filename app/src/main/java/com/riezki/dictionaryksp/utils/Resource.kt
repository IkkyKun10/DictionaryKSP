package com.riezki.dictionaryksp.utils

/**
 * @author riezky maisyar
 */

sealed class Resource<T>(
    val data: T? = null,
    val message: String? = null,
    val statusCode: Int = 0
) {
    class Success<T>(data: T) : Resource<T>(data)
    class Error<T>(statusCode: Int, message: String, data: T? = null) : Resource<T>(data, message, statusCode)
    class Loading<T>(data: T? = null) : Resource<T>(data)

    suspend fun onLoading(block: (T?) -> Unit) : Resource<T> {
        if (this is Loading) block(data)
        return this
    }

    suspend fun onSuccess(block: suspend (T?) -> Unit) : Resource<T> {
        if (this is Success) block(data)
        return this
    }

    suspend fun onFailure(block: suspend (Int, String?, T?) -> Unit) : Resource<T> {
        if (this is Error) block(statusCode, message, data)
        return this
    }
}

enum class ErrorType {
    CLIENT_EXCEPTION,
    HTPP_EXCEPTION,
    SERVER_EXCEPTION,
    UNKNOWN_EXCEPTION,
    IO_EXCEPTION,
    TIMEOUT_EXCEPTION
}