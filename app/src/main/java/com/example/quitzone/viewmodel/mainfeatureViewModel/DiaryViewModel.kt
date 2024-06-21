package com.example.quitzone.ui.mainfeature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quitzone.R
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class DiaryViewModel : ViewModel() {
    private val initialEntries = List(10) { index ->
        DiaryEntry(
            date = "2024-06-20",
            statusIcon = if (index % 2 == 0) R.drawable.ic_happy else R.drawable.ic_sad,
            cravings = index % 10,
            severity = index % 10
        )
    }

    private val _diaryEntries = MutableStateFlow(initialEntries)
    val diaryEntries: StateFlow<List<DiaryEntry>> get() = _diaryEntries.asStateFlow()

    fun addDiaryEntry(entry: DiaryEntry) {
        val icon = if (entry.cravings > 5 || entry.severity > 5) R.drawable.ic_sad else R.drawable.ic_happy
        viewModelScope.launch {
            _diaryEntries.value = _diaryEntries.value + entry.copy(statusIcon = icon)
        }
    }
}

data class DiaryEntry(
    val date: String,
    val statusIcon: Int,
    val cravings: Int,
    val severity: Int
)
