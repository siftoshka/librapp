package az.siftoshka.data.utils

import io.ktor.client.request.HttpRequestBuilder
import io.ktor.client.request.header
import io.ktor.client.request.setBody
import io.ktor.http.ContentType
import io.ktor.http.HttpHeaders
import io.ktor.http.contentType

/**
 * HeaderRequirements - Each of them includes a bunch of headers that is required for specific occasions
 *
 * [HeaderRequirements.Basic] - Just basic required headers
 *
 * [HeaderRequirements.Auth] - [HeaderRequirements.Basic] with Authorization
 */
sealed class HeaderRequirements {
    data object Basic : HeaderRequirements()
    data class Auth(val token: String?) : HeaderRequirements()
}

internal fun HttpRequestBuilder.baseHeaders(
    headers: HeaderRequirements,
    body: Any? = null,
) {
    when (headers) {
        HeaderRequirements.Basic -> basicHeaders(body = body)
        is HeaderRequirements.Auth -> authHeaders(authToken = headers.token, body = body)
    }
}

private fun HttpRequestBuilder.basicHeaders(body: Any? = null) {
    header(HttpHeaders.Accept, ContentType.Application.Json)
    contentType(ContentType.Application.Json)
    header(HttpHeaders.CacheControl, "no-cache")
    body?.let { setBody(body) }
}

private fun HttpRequestBuilder.authHeaders(authToken: String? = null, body: Any? = null) {
    basicHeaders(body)
    authToken?.let { header(HttpHeaders.Authorization, "Bearer $it") }
}