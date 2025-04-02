package com.antmar.screen_fullsize.presentation.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.antmar.screen_fullsize.domain.usecases.GetSharedUrlUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PictureFullSizeViewModel @Inject constructor (
    private val sharedUrl : GetSharedUrlUseCase
) : ViewModel() {

    private val _urlState = MutableStateFlow("")
    val urlState get() = _urlState.asStateFlow()

    init {
        collectUrl()
    }

    private fun collectUrl () {
        viewModelScope.launch {
            sharedUrl.invoke().collect {
                _urlState.value = getHighResUrl(it)
            }
        }
    }

    private fun getHighResUrl(url: String): String {
        return if (url.endsWith("_m.jpg")) {
            url.replace("_m.jpg", "_b.jpg")
        } else url
    }
}