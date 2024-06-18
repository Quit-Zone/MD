package com.example.quitzone.profilingViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class AgeViewModel : ViewModel() {
    private val _age = MutableStateFlow(0)
    val age: StateFlow<Int> = _age

    fun increaseAge() {
        _age.value += 1
    }

    fun decreaseAge() {
        if (_age.value > 0) _age.value -= 1
    }

    fun setAge(newAge: String) {
        val ageInt = newAge.toIntOrNull() ?: 0
        _age.value = ageInt
    }
}
