package com.antmar.screen_fullsize.domain.usecases

import com.antmar.core.data.SharedUrlManager
import javax.inject.Inject

class GetSharedUrlUseCase @Inject constructor(
    private val sharedUrlManager: SharedUrlManager
) {
    operator fun invoke() = sharedUrlManager.sharedUrl
}