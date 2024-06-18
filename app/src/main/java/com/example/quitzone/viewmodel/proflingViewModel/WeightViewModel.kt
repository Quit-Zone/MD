package com.example.quitzone.viewmodel.proflingViewModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State


class WeightViewModel : ViewModel() {
    private val _weight = mutableStateOf("")
    val weight: State<String> = _weight

    fun updateWeight(newWeight: String) {
        _weight.value = newWeight.filter { it.isDigit() || it == '.' }.let {
            if (it.count { char -> char == '.' } <= 1) it else _weight.value
        }
    }

    fun getWeightAsFloat(): Float {
        return _weight.value.toFloatOrNull() ?: 0f
    }
}