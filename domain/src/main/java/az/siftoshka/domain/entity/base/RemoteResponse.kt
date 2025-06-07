package az.siftoshka.domain.entity.base

import az.siftoshka.domain.exceptions.GlobalErrorResponse

sealed class RemoteResponse<out T> {
    data class Success<out T>(val result: T) : RemoteResponse<T>()
    data class Error(val errorCode: Int, val errorResponse: GlobalErrorResponse) : RemoteResponse<Nothing>()
}