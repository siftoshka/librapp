package az.siftoshka.domain.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class LoginRequestModel(
    @SerializedName("jwtToken")
    val token: String,
)