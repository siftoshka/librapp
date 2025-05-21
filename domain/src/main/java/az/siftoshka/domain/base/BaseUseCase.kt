package az.siftoshka.domain.base

interface BaseUseCase<T, R> {

    suspend fun call(input: T): R

}