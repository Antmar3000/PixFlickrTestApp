package com.antmar.screen_gridlist.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antmar.screen_gridlist.domain.usecases.ClearDBUseCase
import com.antmar.screen_gridlist.domain.usecases.GetApiListAndInsertUseCase
import com.antmar.screen_gridlist.domain.usecases.GetDBListUseCase
import com.antmar.screen_gridlist.domain.usecases.SendPictureUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GridViewModel @Inject constructor(
    private val getApiAndInsert : GetApiListAndInsertUseCase,
    private val getDBList : GetDBListUseCase,
    private val clearDatabase : ClearDBUseCase,
    private val sendUrl : SendPictureUrlUseCase
) : ViewModel() {

    private val _pictureListState = MutableStateFlow<List<com.antmar.core.domain.entity.Picture>>(emptyList())
    val pictureListState get() = _pictureListState.asStateFlow()

    init {
        getApiListAndInsert()
        getDBList()
    }

    private fun getApiListAndInsert() {
        viewModelScope.launch {
            getApiAndInsert()
        }
    }

    private fun getDBList() {
        viewModelScope.launch {
            getDBList.invoke().collect{
                _pictureListState.value = it
            }
        }
    }

    fun clearDB() {
        viewModelScope.launch {
            clearDatabase()
        }
    }

    fun sendPictureUrl(url: String) {
        viewModelScope.launch(Dispatchers.IO) {
            sendUrl(url)
        }
    }

}