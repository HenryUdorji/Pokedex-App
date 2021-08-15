/*
package com.henryudorji.pokedex.utils

import android.util.Log
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
        }catch (throwable: Throwable) {
            query().map { Resource.Error(throwable, it) }
        }
    }else {
        query().map { Resource.Success(it) }
    }

    emitAll(flow)
}
*/
