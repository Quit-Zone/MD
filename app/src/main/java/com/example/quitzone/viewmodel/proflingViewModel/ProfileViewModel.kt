package com.example.quitzone.viewmodel.proflingViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quitzone.`class`.Profile
import com.example.quitzone.retrofit.RetrofitInstance
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response

class ProfileViewModel(
    private val ageViewModel: AgeViewModel,
    private val genderViewModel: GenderViewModel,
    private val smokingHabitsViewModel: SmokingHabitsViewModel,
    private val physicalActivityViewModel: PhysicalActivityViewModel,
    private val alcoholConsumptionViewModel: AlcoholConsumptionViewModel,
    private val hobbiesViewModel: HobbiesViewModel,
    private val heightViewModel: HeightViewModel,
    private val weightViewModel: WeightViewModel
) : ViewModel() {

    private val _postResult = MutableStateFlow<Response<Void>?>(null)
    val postResult: StateFlow<Response<Void>?> = _postResult

    fun postProfile() {
        viewModelScope.launch {
            val profile = Profile.Profile(
                age = ageViewModel.age.value,
                gender = genderViewModel.selectedGender ?: "",
                smokinghabit = smokingHabitsViewModel.selectedHabit.value,
                physicalactivity = physicalActivityViewModel.selectedActivity.value,
                alcoholconsumption = alcoholConsumptionViewModel.selectedHabit.value,
                hobby_1 = hobbiesViewModel.selectedHobbies.value.elementAtOrNull(0) ?: "",
                hobby_2 = hobbiesViewModel.selectedHobbies.value.elementAtOrNull(1) ?: "",
                hobby_3 = hobbiesViewModel.selectedHobbies.value.elementAtOrNull(2) ?: "",
                height = heightViewModel.getHeightAsFloat(),
                weight = weightViewModel.getWeightAsFloat()
            )

            try {
                val response = RetrofitInstance.api.postProfile(profile)
                _postResult.value = response
            } catch (e: Exception) {
                // Handle the exception as needed
                e.printStackTrace()
            }
        }
    }
}

class ProfileViewModelFactory(
    private val ageViewModel: AgeViewModel,
    private val genderViewModel: GenderViewModel,
    private val smokingHabitsViewModel: SmokingHabitsViewModel,
    private val physicalActivityViewModel: PhysicalActivityViewModel,
    private val alcoholConsumptionViewModel: AlcoholConsumptionViewModel,
    private val hobbiesViewModel: HobbiesViewModel,
    private val heightViewModel: HeightViewModel,
    private val weightViewModel: WeightViewModel
) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            return ProfileViewModel(
                ageViewModel, genderViewModel, smokingHabitsViewModel,
                physicalActivityViewModel, alcoholConsumptionViewModel,
                hobbiesViewModel, heightViewModel, weightViewModel
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}