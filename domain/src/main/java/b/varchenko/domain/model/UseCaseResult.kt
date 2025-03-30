package b.varchenko.domain.model

data class UseCaseResult<T, E>(
    val data: T? = null,
    val error: E? = null,
)