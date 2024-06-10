package com.example.quitzone.viewmodel
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.example.quitzone.retrofit.RetrofitInstance
import com.example.quitzone.retrofit.SignUpRequest
import com.example.quitzone.retrofit.SignUpResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SignUpViewModel : ViewModel() {
    var signUpState by mutableStateOf("")

    fun signUp(username: String, email: String, password: String) {
        val signUpRequest = SignUpRequest(username, email, password)
        RetrofitInstance.api.signUp(signUpRequest).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    signUpState = "Sign Up Successful: ${response.body()?.message}"
                } else {
                    signUpState = "Sign Up Failed"
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                signUpState = "Network Error: ${t.message}"
            }
        })
    }
}

