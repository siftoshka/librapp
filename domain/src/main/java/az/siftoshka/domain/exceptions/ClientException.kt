package az.siftoshka.domain.exceptions

import com.google.gson.Gson
import com.google.gson.JsonElement
import com.google.gson.annotations.SerializedName

sealed class GlobalErrorResponse {
    data class ClientErrorResponse(
        @SerializedName("status")
        val status: String?,
        @SerializedName("errorResponse")
        val errorResponse: ErrorResponse?
    ) : GlobalErrorResponse() {
        inline fun <reified T> parseAsDataParameters(): T {
            return Gson().fromJson(errorResponse?.extraData.toString(), T::class.java)
        }
    }

    data class NetworkResponse(val e: Exception) : GlobalErrorResponse()
}

data class ErrorResponse(
    @SerializedName("title")
    val title: String?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("data")
    val extraData: JsonElement? = null,
)
