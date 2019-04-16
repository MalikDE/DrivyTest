package com.mde.services.utils


/**
 * A generic class that holds a value with its loading status.
 * Inspired from google aac samples
 */
data class Resource<out T>(val status: Status, val data: T?, val message: String?) {
    companion object {
        fun <T> success(data: T?): Resource<T> {
            return Resource(Status.SUCCESS, data, null)
        }

        fun <T> error(msg: String, data: T? = null): Resource<T> {
            return Resource(Status.ERROR, data, msg)
        }

        fun <T> loading(data: T?): Resource<T> {
            return Resource(Status.LOADING, data, null)
        }
    }

    override fun toString(): String {
        return "Resource{status=$status, message='$message${'\''}, data=$data${'}'}"
    }
}

