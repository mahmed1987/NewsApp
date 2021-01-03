package com.naggaro.newsapp.network

import com.google.gson.JsonSyntaxException
import com.google.gson.stream.MalformedJsonException
import com.naggaro.common.error.Failure
import com.naggaro.common.newsapp.functional.Either
import retrofit2.Call
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * Takes in a transform lambda to return a modified version of the response
 */
fun <T, R> Call<T>.requestBlocking(transform: (T) -> R): Either<Failure, R> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right(transform(response.body()!!))
            false -> when (response.code()) {
                401 -> Either.Left(Failure.AuthError)
                403 -> Either.Left(Failure.Forbidden)
                400 -> Either.Left(Failure.BadRequest)
                404 -> Either.Left(Failure.NotFound)
                415 -> Either.Left(Failure.UnSupportedMediaType)
                500 -> Either.Left(Failure.InternalServerError)
                else -> Either.Left(Failure.ServerError)
            }
        }
    } catch (exception: Throwable) {
        when (exception) {
            is SecurityException -> Either.Left(Failure.AndroidError)
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            is MalformedJsonException -> Either.Left(Failure.MalFormedJson)
            is IllegalStateException -> Either.Left(Failure.IllegalStateException)
            is JsonSyntaxException -> Either.Left(Failure.JsonSyntaxException)
            is SocketTimeoutException -> Either.Left(Failure.SocketTimedOutException)
            else -> Either.Left(Failure.ServerError)
        }
    }
}

fun <T> Call<T>.requestBlocking(): Either<Failure, T> {
    return try {
        val response = execute()
        when (response.isSuccessful) {
            true -> Either.Right((response.body()!!))
            false -> when (response.code()) {
                401 -> Either.Left(Failure.AuthError)
                403 -> Either.Left(Failure.Forbidden)
                400 -> Either.Left(Failure.BadRequest)
                404 -> Either.Left(Failure.NotFound)
                415 -> Either.Left(Failure.UnSupportedMediaType)
                500 -> Either.Left(Failure.InternalServerError)
                else -> Either.Left(Failure.ServerError)
            }
        }
    } catch (exception: Throwable) {
        when (exception) {
            is SecurityException -> Either.Left(Failure.AndroidError)
            is UnknownHostException -> Either.Left(Failure.NetworkConnection)
            is MalformedJsonException -> Either.Left(Failure.MalFormedJson)
            is IllegalStateException -> Either.Left(Failure.IllegalStateException)
            is JsonSyntaxException -> Either.Left(Failure.JsonSyntaxException)
            else -> Either.Left(Failure.ServerError)
        }
    }
}


