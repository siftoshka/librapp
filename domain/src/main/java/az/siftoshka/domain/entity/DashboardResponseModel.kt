package az.siftoshka.domain.entity

import com.google.gson.annotations.SerializedName
import kotlinx.serialization.Serializable

@Serializable
data class DashboardResponseModel(
    @SerializedName("lists")
    val lists: List<DashboardResponseListModel>
)

@Serializable
data class DashboardResponseListModel(
    @SerializedName("id")
    val id: Int,
    @SerializedName("title")
    val title: String,
    @SerializedName("apps")
    val apps: List<AppResponseModel>,
)

@Serializable
data class AppResponseModel(
    @SerializedName("icon")
    val icon: String,
    @SerializedName("title")
    val title: String,
    @SerializedName("url")
    val url: String
)