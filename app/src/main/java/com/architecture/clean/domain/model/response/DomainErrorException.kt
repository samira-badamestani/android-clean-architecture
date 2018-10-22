package com.architecture.clean.domain.model.response



class DomainErrorException(val errorModel: ErrorModel): Throwable() {
}