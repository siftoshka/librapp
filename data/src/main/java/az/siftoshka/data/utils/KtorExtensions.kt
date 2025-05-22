package az.siftoshka.data.utils

import az.siftoshka.data.BuildConfig
import az.siftoshka.domain.entity.RemoteResponse
import az.siftoshka.domain.exceptions.ErrorResponse
import az.siftoshka.domain.exceptions.GlobalErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.header
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

inline fun <reified Response, reified Body> HttpClient.safeRequest(
    requestType: RequestType,
    path: String,
    body: Body,
): Flow<RemoteResponse<Response>> = flow {
    try {
        val basePath = BuildConfig.BASE_URL
        val response = request(requestType, basePath + path) { headers(body) }

        when (response.status.value) {
            in 200..299 -> {
                val responseBody = response.body<Response>()
                emit(RemoteResponse.Success(responseBody))
            }

            in 300..399 -> {
                emit(RemoteResponse.Error(0, GlobalErrorResponse.NetworkResponse(Exception())))
            }

            in 400..499 -> {
                val apiError = response.body<GlobalErrorResponse.ClientErrorResponse>()

                emit(
                    RemoteResponse.Error(
                        errorCode = response.status.value,
                        errorResponse = GlobalErrorResponse.ClientErrorResponse(
                            status = apiError.status,
                            errorResponse = ErrorResponse(
                                title = apiError.errorResponse?.title,
                                message = apiError.errorResponse?.message,
                                extraData = apiError.errorResponse?.extraData,
                            )
                        )
                    )
                )
            }

            in 500..599 -> {
                emit(
                    RemoteResponse.Error(
                        errorCode = response.status.value,
                        errorResponse = GlobalErrorResponse.NetworkResponse(Exception())
                    )
                )
            }
        }
    } catch (e: Exception) {
        emit(
            RemoteResponse.Error(
                errorCode = 0,
                errorResponse = GlobalErrorResponse.NetworkResponse(e)
            )
        )
    }
}

suspend fun HttpClient.request(
    requestType: RequestType,
    path: String,
    block: HttpRequestBuilder.() -> Unit = {}
): HttpResponse {
    return when (requestType) {
        RequestType.GET -> get(path) { block }
        RequestType.PUT -> put(path) { block }
        RequestType.PATCH -> patch(path) { block }
        RequestType.POST -> post(path) { block }
        RequestType.DELETE -> delete(path) { block }
    }
}

inline fun <reified Body> HttpRequestBuilder.headers(body: Body) {
    header(HttpHeaders.Authorization, "Bearer")
    header(HttpHeaders.Accept, ContentType.Application.Json)
    contentType(ContentType.Application.Json)
    header(HttpHeaders.CacheControl, "no-cache")
    setBody(body)
}

enum class RequestType {
    GET, PUT, PATCH, POST, DELETE
}