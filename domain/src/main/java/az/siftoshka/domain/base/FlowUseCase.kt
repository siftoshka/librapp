package az.siftoshka.domain.base

import az.siftoshka.domain.entity.base.RemoteResponse
import kotlinx.coroutines.flow.Flow

interface FlowUseCase<T, R> {
    suspend fun call(input: T): Flow<RemoteResponse<R>>
}