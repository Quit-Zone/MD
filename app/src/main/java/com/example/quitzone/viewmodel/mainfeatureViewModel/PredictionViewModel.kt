package com.example.quitzone.viewmodel.mainfeatureViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.CreationExtras
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.retrofit.RetrofitInstance
import kotlinx.coroutines.launch
import retrofit2.Response


class PredictionViewModel(private val sharedpreferences: Sharedpreferences) : ViewModel() {
    private val api = RetrofitInstance.api

    private val _predictionCategory = MutableLiveData<String>()
    val predictionCategory: LiveData<String> = _predictionCategory

    fun postPredict(token: String) {
        viewModelScope.launch {
            try {
                val response: Response<Void> = api.postPredict("Bearer $token")
                if (response.isSuccessful) {
                    // Handle successful response
                    Log.d("PredictionViewModel", "Prediction successful")
                    println("Prediction successful")
                } else {
                    // Handle unsuccessful response
                    Log.e("PredictionViewModel", "Prediction failed: ${response.errorBody()?.string()}")
                    println("Prediction failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                // Handle error
                Log.e("PredictionViewModel", "Prediction failed: ${e.message}")
                println("Prediction failed: ${e.message}")
            }
        }
    }

    fun getPrediction(token: String) {
        viewModelScope.launch {
            try {
                val response = api.getPrediction("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let {
                        sharedpreferences.setPredictionValue(it.data.kategori)
                        Log.d("PredictionViewModel", "Prediction successful: ${it.data.kategori}")
                        println("Prediction successful: ${it.data.kategori}")
                    } ?: run {
                        Log.e("PredictionViewModel", "Response body is null")
                        println("Response body is null")
                    }
                } else {
                    Log.e("PredictionViewModel", "Prediction failed: ${response.errorBody()?.string()}")
                    println("Prediction failed: ${response.errorBody()?.string()}")
                }
            } catch (e: Exception) {
                Log.e("PredictionViewModel", "Prediction failed: ${e.message}")
                println("Prediction failed: ${e.message}")
            }

            if (sharedpreferences.getPredictionValue() == "Physically Active"){
                sharedpreferences.setPredictionValue("Physically")
            }else if (sharedpreferences.getPredictionValue() == "Creatively Engaged") {
                sharedpreferences.setPredictionValue("Creatively")
            }else if (sharedpreferences.getPredictionValue() == "Relaxed and Leisurely") {
                sharedpreferences.setPredictionValue("Relaxed")
            }else {
                sharedpreferences.setPredictionValue("Socially")
            }
        }
    }
}



class PredictionViewModelFactory(private val sharedpreferences: Sharedpreferences) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>, extras: CreationExtras): T {
        if (modelClass.isAssignableFrom(PredictionViewModel::class.java)) {
            return PredictionViewModel(sharedpreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
