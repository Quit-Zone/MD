package com.example.quitzone.profilingViewModel

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quitzone.retrofit.LoginRequest
import com.example.quitzone.retrofit.LoginResponse
import com.example.quitzone.retrofit.RetrofitInstance
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginViewModel : ViewModel() {
    private val _loginState = MutableLiveData<String>()
    val loginState: LiveData<String> get() = _loginState

    var userId by mutableStateOf("")
    var userName by mutableStateOf("")
    var userEmail by mutableStateOf("")

    fun login(email: String, password: String) {
        val loginRequest = LoginRequest(email, password)
        RetrofitInstance.api.login(loginRequest).enqueue(object : Callback<LoginResponse> {
            override fun onResponse(call: Call<LoginResponse>, response: Response<LoginResponse>) {
                if (response.isSuccessful) {
                    val loginResponse = response.body()
                    if (loginResponse != null && loginResponse.data != null) {
                        userId = loginResponse.token
                        userName = loginResponse.data.username
                        userEmail = loginResponse.data.email
                        _loginState.value = "Login Successful"
                    } else {
                        _loginState.value = "Login Failed: Invalid response data"
                        Log.e("LoginViewModel", "Invalid response data: ${response.body()}")
                    }
                } else {
                    _loginState.value = "Login Failed: ${response.code()}"
                    Log.e("LoginViewModel", "Response code: ${response.code()}")
                }
            }

            override fun onFailure(call: Call<LoginResponse>, t: Throwable) {
                _loginState.value = "Network Error: ${t.message}"
                Log.e("LoginViewModel", "Network error: ${t.message}")
            }
        })
    }
}




