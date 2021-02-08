package com.naggaro.common.newsapp.base

import com.naggaro.common.error.Failure
import com.naggaro.common.newsapp.functional.Either
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

abstract class BaseUseCase<out Type, in Params>(private val ioScope: CoroutineScope) where Type : Any? {
    abstract suspend fun run(param: Params): Either<Failure, Type>
    operator fun invoke(params: Params, onResult: (Either<Failure, Type>) -> Unit) {
        ioScope.launch {
            val result = run(params)
            withContext(Dispatchers.Main)
            {
                onResult(result)
            }
        }
    }
    class None
}