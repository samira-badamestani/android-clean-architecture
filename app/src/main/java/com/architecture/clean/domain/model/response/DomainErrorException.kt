package com.architecture.clean.domain.model.response

import com.architecture.clean.domain.model.response.ErrorModel

class DomainErrorException(val errorModel: ErrorModel): Throwable() {
}