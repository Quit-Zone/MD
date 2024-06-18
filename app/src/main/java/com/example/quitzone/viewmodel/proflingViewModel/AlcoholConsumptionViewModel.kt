package com.example.quitzone.viewmodel.proflingViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class AlcoholConsumptionViewModel : ViewModel() {
    private val _selectedHabit = mutableStateOf("Never")
    val selectedHabit = _selectedHabit

    fun onHabitSelected(habit: String) {
        _selectedHabit.value = habit
    }
}
