package com.antmar.screen_gridlist.domain.usecases

import com.antmar.screen_gridlist.data.room.RoomRepository
import javax.inject.Inject

class GetDBListUseCase @Inject constructor(
    private val roomRepository: RoomRepository
) {
    fun invoke() = roomRepository.getAllFromDB()
}