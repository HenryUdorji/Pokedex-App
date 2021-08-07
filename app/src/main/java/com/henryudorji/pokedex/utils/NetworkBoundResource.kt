package com.henryudorji.pokedex.utils

import kotlinx.coroutines.flow.*

inline fun <ResultType, RequestType> networkBoundResource(
    crossinline query: () -> Flow<ResultType>,
    crossinline fetch: suspend () -> RequestType,
    crossinline saveFetchedResult: suspend (RequestType) -> Unit,
    crossinline shouldFetch: (ResultType) -> Boolean = { true }
) = flow {
    val data = query().first()

    val flow = if (shouldFetch(data)) {
        emit(Resource.Loading(data))
        try {
            saveFetchedResult(fetch())
            query().map { Resource.Success(it) }
        }catch (e: Exception) {
            val message = if (e.message != null) e.message else "SHOULD_FETCH Error"
            query().map { Resource.Error(message!!, it) }
        }
    }else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}