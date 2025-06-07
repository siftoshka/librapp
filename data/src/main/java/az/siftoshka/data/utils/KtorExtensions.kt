package az.siftoshka.data.utils

import az.siftoshka.data.BuildConfig
import az.siftoshka.data.datastore.AppSettingsPreferencesManager
import az.siftoshka.domain.entity.base.RemoteResponse
import az.siftoshka.domain.exceptions.ErrorResponse
import az.siftoshka.domain.exceptions.GlobalErrorResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.delete
import io.ktor.client.request.get
import io.ktor.client.request.patch
import io.ktor.client.request.post
import io.ktor.client.request.put
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.flow
import org.koin.core.context.GlobalContext

inline fun <reified Response> HttpClient.safeRequest(
    requestType: RequestType,
    headerType: HeaderType = HeaderType.AUTH,
    basePath: String = BuildConfig.BASE_URL,
    path: String,
    body: Any? = null,
): Flow<RemoteResponse<Response>> = flow {
    try {
        val response = request(
            requestType = requestType,
            headerType = headerType,
            path = basePath + path,
            body = body
        )

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

            else -> {
                emit(RemoteResponse.Error(0, GlobalErrorResponse.NetworkResponse(Exception())))
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
    headerType: HeaderType,
    path: String,
    body: Any? = null,
): HttpResponse {

    val headers = getHeaderRequirements(headerType)

    return when (requestType) {
        RequestType.GET -> get(path) { baseHeaders(headers) }
        RequestType.PUT -> put(path) { baseHeaders(headers, body) }
        RequestType.PATCH -> patch(path) { baseHeaders(headers, body) }
        RequestType.POST -> { post(path) { baseHeaders(headers, body) } }

        RequestType.DELETE -> delete(path) { baseHeaders(headers, body) }
    }
}

private suspend fun getHeaderRequirements(headerType: HeaderType) = when (headerType) {
    HeaderType.BASIC -> HeaderRequirements.Basic
    HeaderType.AUTH -> {
        val authToken = GlobalContext.get().get<AppSettingsPreferencesManager>()
            .getAppSettings()
            .firstOrNull()
            ?.token
        HeaderRequirements.Auth(authToken)
    }
}

enum class RequestType {
    GET, PUT, PATCH, POST, DELETE
}

enum class HeaderType {
    BASIC, AUTH
}