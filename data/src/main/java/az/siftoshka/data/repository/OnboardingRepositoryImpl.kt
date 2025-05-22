package az.siftoshka.data.repository

import az.siftoshka.data.utils.RequestType
import az.siftoshka.data.utils.safeRequest
import az.siftoshka.domain.entity.LoginRequestModel
import az.siftoshka.domain.entity.RemoteResponse
import az.siftoshka.domain.repository.OnboardingRepository
import az.siftoshka.domain.utils.NetworkPaths
import io.ktor.client.HttpClient
import kotlinx.coroutines.flow.Flow

class OnboardingRepositoryImpl(
    private val httpClient: HttpClient
) : OnboardingRepository {

    override suspend fun login(requestModel: LoginRequestModel): Flow<RemoteResponse<Unit>> {
        return httpClient.safeRequest(
            requestType = RequestType.POST,
            path = NetworkPaths.Auth.Login,
            body = requestModel
        )
    }
}