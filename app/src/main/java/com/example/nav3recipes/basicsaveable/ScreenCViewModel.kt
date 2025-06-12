package com.example.nav3recipes.basicsaveable

import androidx.compose.runtime.mutableIntStateOf
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.SavedStateHandleSaveableApi
import androidx.lifecycle.viewmodel.compose.saveable

class ScreenCViewModel(
    savedStateHandle: SavedStateHandle,
) : ViewModel() {

    val keys = savedStateHandle.keys().toList()
    val navKey = savedStateHandle.get<String>("NavKey")

    val name = navKey ?: "ScreenC"
    val message = "Hello from ScreenC"


    @OptIn(SavedStateHandleSaveableApi::class)
    var counter by savedStateHandle.saveable {
        mutableIntStateOf(0)
    }

    fun increment() {
        counter++
    }

}