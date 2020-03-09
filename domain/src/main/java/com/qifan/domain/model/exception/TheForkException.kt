package com.qifan.domain.model.exception


sealed class TheForkException(message: String? = null, throwable: Throwable? = null) :
    Exception(message, throwable) {
    class GeneralException(throwable: Throwable?) : TheForkException()
    class EmptyException(message: String? = "Empty Restaurant Found") : TheForkException(message)
    class NetworkException(message: String?=null, throwable: Throwable? = null) :
        TheForkException(message, throwable)
}