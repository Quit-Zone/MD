package com.example.quitzone.retrofit

import com.example.quitzone.`class`.User
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(val token: String, val user: User.User)

data class SignUpRequest(val username: String, val email: String, val password: String)
data class SignUpResponse(val message: String)

interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>
    @POST("register")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>
}

