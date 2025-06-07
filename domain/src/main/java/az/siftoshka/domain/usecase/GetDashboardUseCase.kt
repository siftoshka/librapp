package az.siftoshka.domain.usecase

import az.siftoshka.domain.base.FlowUseCase
import az.siftoshka.domain.entity.DashboardResponseModel
import az.siftoshka.domain.entity.base.RemoteResponse
import az.siftoshka.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetDashboardUseCase(
    private val homeRepository: HomeRepository
) : FlowUseCase<Unit, DashboardResponseModel> {

    override suspend fun call(input: Unit): Flow<RemoteResponse<DashboardResponseModel>> {
        return homeRepository.dashboard()
    }
}