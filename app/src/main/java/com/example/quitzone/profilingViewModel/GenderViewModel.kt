package com.example.quitzone.profilingViewModel

import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue

class GenderViewModel : ViewModel() {
    var selectedGender by mutableStateOf<String?>(null)
        private set

    fun selectGender(gender: String) {
        selectedGender = gender
    }
}
