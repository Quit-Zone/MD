package com.example.quitzone.profilingViewModel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel

class PhysicalActivityViewModel : ViewModel() {
    private val _selectedActivity = mutableStateOf("Sedentary (little or no exercise)")
    val selectedActivity = _selectedActivity

    fun onActivitySelected(activity: String) {
        _selectedActivity.value = activity
    }
}
