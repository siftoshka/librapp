package az.siftoshka.data.repository

import az.siftoshka.data.utils.RequestType
import az.siftoshka.data.utils.safeRequest
import az.siftoshka.domain.entity.DashboardResponseModel
import az.siftoshka.domain.entity.base.RemoteResponse
import az.siftoshka.domain.repository.HomeRepository
import az.siftoshka.domain.utils.NetworkPaths
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class HomeRepositoryImpl(
    private val httpClient: HttpClient
) : HomeRepository {

    override suspend fun dashboard(): Flow<RemoteResponse<DashboardResponseModel>> {
        return httpClient.safeRequest(
            requestType = RequestType.GET,
            path = NetworkPaths.Home.Dashboard
        )
    }
}