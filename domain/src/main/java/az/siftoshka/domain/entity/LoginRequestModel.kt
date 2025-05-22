package az.siftoshka.domain.entity

import com.google.gson.annotations.SerializedName

data class LoginRequestModel(
    @SerializedName("jwtToken")
    val token: String,
)