package com.architecture.clean.domain.usecase.base

abstract class UseCase<T> {

    abstract fun execute(): T

}