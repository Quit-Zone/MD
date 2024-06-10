package com.example.quitzone.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.quitzone.retrofit.LoginRequest
import com.example.quitzone.retrofit.LoginResponse
import com.example.quitzone.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    var loginState by mutableStateOf("")

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    loginState = "Login Successful: ${response.body()?.token}"
                } else {
                    loginState = "Login Failed"
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                loginState = "Network Error: ${t.message}"
            }
        })
    }
}