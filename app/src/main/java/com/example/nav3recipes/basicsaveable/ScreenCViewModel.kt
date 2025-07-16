package com.example.nav3recipes.basicsaveable

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn

class ScreenCViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val keys = savedStateHandle.keys().toList()

    val name = savedStateHandle
        .getStateFlow<RouteC?>("NavKey", null)
        .map { it?.id?.toString() ?: "ScreenC" }
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = "ScreenC"
        )

    val message = "Hello from ScreenC"

    @OptIn(SavedStateHandleSaveableApi::class)
    var counter by savedStateHandle.saveable {
        mutableIntStateOf(0)
    }

    fun increment() {
        counter++
    }

}