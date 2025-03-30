package b.varchenko.domain.utils

import b.varchenko.domain.model.UseCaseResult

inline fun <T, E> UseCaseResult<T, E>.onSuccess(
    onSuccess: (data: T) -> Unit,
): UseCaseResult<T, E> {
    if (data != null) {
        onSuccess(data)
    }
    return this
}

inline fun <T, E> UseCaseResult<T, E>.onError(
    onError: (error: E) -> Unit,
) : UseCaseResult<T, E>{
    if (error != null) {
        onError(error)
    }
    return this
}