package com.antmar.core.data

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedUrlManager @Inject constructor() {

    private val _sharedUrl = MutableSharedFlow<String>()
    val sharedUrl get() = _sharedUrl.asSharedFlow()

    suspend fun updateSharedUrl(url: String) {
        _sharedUrl.emit(url)
    }
}