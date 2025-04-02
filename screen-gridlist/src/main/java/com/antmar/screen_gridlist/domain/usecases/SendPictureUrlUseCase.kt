package com.antmar.screen_gridlist.domain.usecases

import com.antmar.core.data.SharedUrlManager
import javax.inject.Inject

class SendPictureUrlUseCase @Inject constructor(
    private val sharedUrlManager: SharedUrlManager
) {
    suspend operator fun invoke (url : String) {
        sharedUrlManager.updateSharedUrl(url)
    }
}