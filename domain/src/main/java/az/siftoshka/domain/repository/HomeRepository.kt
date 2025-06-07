package az.siftoshka.domain.repository

import az.siftoshka.domain.entity.DashboardResponseModel
import az.siftoshka.domain.entity.base.RemoteResponse
import kotlinx.coroutines.flow.Flow

interface HomeRepository {

    suspend fun dashboard(): Flow<RemoteResponse<DashboardResponseModel>>

}