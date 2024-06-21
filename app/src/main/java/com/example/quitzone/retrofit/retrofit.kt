package com.example.quitzone.retrofit

import com.example.quitzone.`class`.Profile
import com.example.quitzone.`class`.User
import com.google.gson.annotations.SerializedName
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST

data class LoginRequest(val email: String, val password: String)
data class LoginResponse(
    val status: String,
    val id: String,
    val message: String,
    val token: String,
    val data: User
)

data class SignUpRequest(val username: String, val email: String, val password: String)
data class SignUpResponse(
    val id: String,
    val name: String,
    val email: String,
    val message: String // This might include a success message
)

data class PredictionResponse(
//    @SerializedName("status")
    val status: String,
//    @SerializedName("data")
    val data: DataPredict
)

data class DataPredict(
//    @SerializedName("kategori")
    val kategori: String
)

data class  Wallet(
    val amount : Double
)

data class WalletResponse(
    val status: String,
    val data: List<DataWallet> // Changed from DataWallet to List<DataWallet>
)

data class DataWallet(
    val amountget: Double
)


interface ApiService {
    @POST("login")
    fun login(@Body request: LoginRequest): Call<LoginResponse>

    @POST("register")
    fun signUp(@Body request: SignUpRequest): Call<SignUpResponse>

    @POST("profile")
    suspend fun postProfile(@Header("Authorization") token: String, @Body profile: Profile): Response<Void>

    @POST("predict")
    suspend fun postPredict(@Header("Authorization") token: String) : Response<Void>

    @GET("predict")
    suspend fun getPrediction(@Header("Authorization") token: String): Response<PredictionResponse>

    @POST("wallet")
    suspend fun postWallet(@Header("Authorization") token: String, @Body wallet: Wallet) : Response<Void>

    @GET("wallet")
    suspend fun getWallet(@Header("Authorization") token: String) : Response<WalletResponse>
}


