package com.example.quitzone.viewmodel.profilingViewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quitzone.`class`.Profile
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.retrofit.ApiService
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@HiltViewModel
class ProfileViewModel @Inject constructor(
    private val apiService: ApiService,
    private val sharedpreferences: Sharedpreferences
) : ViewModel() {


    private val _profilePostResult = MutableStateFlow<Result<Unit>?>(null)
    val profilePostResult: StateFlow<Result<Unit>?> = _profilePostResult

    fun collectProfileData(): Profile {
        return Profile(
            age = sharedpreferences.getAge(),
            gender = sharedpreferences.getGender().toString(),
            smokingHabit = sharedpreferences.getSmokingHabit().toString(),
            physicalActivity = sharedpreferences.getPhysicalActivity().toString(),
            alcoholConsumption = sharedpreferences.getAlcoholConsumption().toString(),
            hobby_1 = sharedpreferences.getHobby1().toString(),
            hobby_2 = sharedpreferences.getHobby2().toString(),
            hobby_3 = sharedpreferences.getHobby3().toString(),
            height = sharedpreferences.getHeight(),
            weight = sharedpreferences.getWeight()
        )
    }

    fun postProfile(Token: String) {
        viewModelScope.launch {
            try {
                val profile = collectProfileData()

                // Ensure the token is not empty
//                val token = loginViewModel.userId
                if (Token.isEmpty()) {
                    _profilePostResult.value = Result.failure(Exception("Token is empty"))
                    return@launch
                }

                // Call API service to post profile data with Authorization header
                val response = apiService.postProfile("Bearer $Token", profile)
                if (response.isSuccessful) {
                    _profilePostResult.value = Result.success(Unit)
                } else {
                    _profilePostResult.value = Result.failure(Exception("Error: ${response.code()}"))
                }
            } catch (e: Exception) {
                _profilePostResult.value = Result.failure(e)
            }
        }
    }
}


@Singleton
class ProfileViewModelFactory @Inject constructor(
    private val apiService: ApiService,
    private val sharedpreferences: Sharedpreferences
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(ProfileViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return ProfileViewModel(
                apiService,
                sharedpreferences
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}






