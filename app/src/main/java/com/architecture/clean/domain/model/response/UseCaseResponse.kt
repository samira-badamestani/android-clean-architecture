package  com.architecture.clean.domain.model.response



/**
 * base sealed class for handling UseCase responses in [BaseUseCase]
 * @see [BaseUseCase]
 */
sealed class UseCaseResponse<out T>

/**
 * Wrapper for success response of repository calls
 */
data class SuccessResponse<out T>(val value: T): UseCaseResponse<T>()

/**
 * Wrapper for error response of repository calls
 */
data class ErrorResponse<out T>(val error: ErrorModel): UseCaseResponse<T>()