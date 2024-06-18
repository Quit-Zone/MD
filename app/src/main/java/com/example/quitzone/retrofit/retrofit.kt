package com.example.quitzone.retrofit

import com.example.quitzone.`class`.Profile
import com.example.quitzone.`class`.User
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.Header
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val status: String,
    val message: String,
    val token: String,
    val data: User
)

data class SignUpRequest(val username: String, val email: String, val password: String)
data class SignUpResponse(
    val id: Int,
    val name: String,
    val email: String,
    val message: String // This might include a success message
)


interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST("profile")
    suspend fun postProfile(@Header("Authorization") token: String, @Body profile: Profile): Response<Void>
}


