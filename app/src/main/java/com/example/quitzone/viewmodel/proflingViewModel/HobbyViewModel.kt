package com.example.quitzone.viewmodel.proflingViewModel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class HobbiesViewModel : ViewModel() {
    private val _selectedHobbies = MutableStateFlow(setOf<String>())
    val selectedHobbies: StateFlow<Set<String>> = _selectedHobbies

    fun toggleHobby(hobby: String) {
        _selectedHobbies.value = if (_selectedHobbies.value.contains(hobby)) {
            _selectedHobbies.value - hobby
        } else {
            if (_selectedHobbies.value.size < 3) {
                _selectedHobbies.value + hobby
            } else {
                _selectedHobbies.value
            }
        }
    }
}
