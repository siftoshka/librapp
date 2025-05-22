package az.siftoshka.domain.repository

import az.siftoshka.domain.entity.LoginRequestModel
import az.siftoshka.domain.entity.RemoteResponse
import kotlinx.coroutines.flow.Flow

interface OnboardingRepository {

    suspend fun login(requestModel: LoginRequestModel): Flow<RemoteResponse<Unit>>
}