package com.antmar.screen_gridlist.domain.usecases

import com.antmar.screen_gridlist.data.room.RoomRepository
import javax.inject.Inject

class ClearDBUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    suspend operator fun invoke() {
        roomRepository.clearDB()
    }
}