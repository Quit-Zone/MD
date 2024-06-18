package com.example.quitzone.profilingViewModel
import androidx.lifecycle.ViewModel
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.State
import androidx.compose.ui.text.input.TextFieldValue

class CitySelectionViewModel : ViewModel() {
    private val _selectedCity = mutableStateOf(TextFieldValue(""))
    val selectedCity: State<TextFieldValue> = _selectedCity

    private val _cities = listOf("Aceh",
        "Bali",
        "Banten",
        "Bengkulu",
        "D.I Yogyakarta",
        "D.K.I Jakarta",
        "Gorontalo",
        "Jambi",
        "Jawa Barat",
        "Jawa Tengah",
        "Jawa Timur",
        "Kalimantan Barat",
        "Kalimantan Selatan",
        "Kalimantan Tengah",
        "Kalimantan Timur",
        "Kalimantan Utara",
        "Kepulauan Bangka Belitung",
        "Kepulauan Riau",
        "Lampung",
        "Maluku",
        "Maluku Utara",
        "Nusa Tenggara Barat",
        "Nusa Tenggara Timur",
        "Papua",
        "Papua Barat",
        "Riau",
        "Sulawesi Barat",
        "Sulawesi Selatan",
        "Sulawesi Tengah",
        "Sulawesi Tenggara",
        "Sulawesi Utara",
        "Sumatera Barat",
        "Sumatera Selatan",
        "Sumatera Utara")
    private val _filteredCities = mutableStateOf(_cities)
    val filteredCities: State<List<String>> = _filteredCities

    fun onCityChange(newValue: TextFieldValue) {
        _selectedCity.value = newValue
        _filteredCities.value = _cities.filter { it.contains(newValue.text, ignoreCase = true) }
    }

    fun onCitySelected(city: String) {
        _selectedCity.value = TextFieldValue(city)
    }
}