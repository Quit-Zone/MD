package com.example.quitzone.viewmodel.proflingViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State

class HeightViewModel : ViewModel() {
    private val _height = mutableStateOf("")
    val height: State<String> = _height

    fun updateHeight(newHeight: String) {
        _height.value = newHeight.filter { it.isDigit() }
    }

    fun getHeightAsFloat(): Float {
        return _height.value.toFloatOrNull() ?: 0f
    }
}