package com.example.quitzone.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class SmokingHabitsViewModel : ViewModel() {
    private val _selectedHabit = mutableStateOf("Never")
    val selectedHabit = _selectedHabit

    fun onHabitSelected(habit: String) {
        _selectedHabit.value = habit
    }
}
