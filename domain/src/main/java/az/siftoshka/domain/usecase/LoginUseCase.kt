package az.siftoshka.domain.usecase

import az.siftoshka.domain.base.FlowUseCase
import az.siftoshka.domain.entity.LoginRequestModel
import az.siftoshka.domain.entity.RemoteResponse
import az.siftoshka.domain.repository.OnboardingRepository
import kotlinx.coroutines.flow.Flow

class LoginUseCase(
    private val onboardingRepository: OnboardingRepository
) : FlowUseCase<String, Unit> {
    override suspend fun call(input: String): Flow<RemoteResponse<Unit>> {
        return onboardingRepository.login(LoginRequestModel(input))
    }
}