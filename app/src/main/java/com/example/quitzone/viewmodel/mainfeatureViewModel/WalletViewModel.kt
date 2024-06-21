package com.example.quitzone.viewmodel.mainfeatureViewModel

import android.content.SharedPreferences
import androidx.compose.runtime.State
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.retrofit.RetrofitInstance
import com.example.quitzone.retrofit.Wallet
import kotlinx.coroutines.launch

//class WalletViewModel(private val sharedpreferences: Sharedpreferences) : ViewModel() {
//
//    private val _text = mutableStateOf("")
//    val text: State<String> = _text
//
//    private val _responseMessage = MutableLiveData<String>()
//    val responseMessage: LiveData<String> = _responseMessage
//
//    var amounttotal by mutableStateOf(0.0)
//
//    fun onTextChanged(newText: String) {
//        _text.value = newText
//    }
//
//    fun postWallet(token: String) {
//        viewModelScope.launch {
//            try {
//                val walletAmount = _text.value.toDouble()
//                val response = RetrofitInstance.api.postWallet("Bearer $token", Wallet(walletAmount))
//                if (response.isSuccessful) {
//                    _responseMessage.value = "Wallet posted successfully"
//                } else {
//                    _responseMessage.value = "Failed to post wallet: ${response.errorBody()?.string()}"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Exception occurred: ${e.message}"
//            }
//            println(responseMessage.value)
//        }
//    }
//
//    fun getWallet(token: String) {
//        viewModelScope.launch {
//            try {
//                val response = RetrofitInstance.api.getWallet("Bearer $token")
//                if (response.isSuccessful) {
//                    response.body()?.let { walletResponse ->
//                        val amount = walletResponse.data.amountget
//                        amounttotal = amount
//                        sharedpreferences.setWalletValue(amount)
//                    }
//                    _responseMessage.value = "Wallet retrieved successfully"
//                } else {
//                    _responseMessage.value = "Failed to retrieve wallet: ${response.errorBody()?.string()}"
//                }
//            } catch (e: Exception) {
//                _responseMessage.value = "Exception occurred: ${e.message}"
//            }
//            println(responseMessage.value)
//        }
//    }
//}

class WalletViewModel(private val sharedpreferences: Sharedpreferences) : ViewModel() {

    private val _text = mutableStateOf("")
    val text: State<String> = _text

    private val _responseMessage = MutableLiveData<String>()
    val responseMessage: LiveData<String> = _responseMessage

    var amounttotal by mutableStateOf(0.0)

    fun onTextChanged(newText: String) {
        _text.value = newText
    }

    fun clearText() {
        _text.value = ""
    }

    fun walletLocalPost() {
        val currentWalletValue = sharedpreferences.getLocalWalletValue()
        val newWalletValue = currentWalletValue + _text.value.toInt()
        sharedpreferences.setLocalWalletValue(newWalletValue)
    }

    fun postWallet(token: String) {
        viewModelScope.launch {
            try {
                val walletAmount = _text.value.toDouble()
                val response = RetrofitInstance.api.postWallet("Bearer $token", Wallet(walletAmount))
                if (response.isSuccessful) {
                    _responseMessage.value = "Wallet posted successfully"
                } else {
                    _responseMessage.value = "Failed to post wallet: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _responseMessage.value = "Exception occurred: ${e.message}"
            }
            println(responseMessage.value)
        }
    }

    fun getWallet(token: String) {
        viewModelScope.launch {
            try {
                val response = RetrofitInstance.api.getWallet("Bearer $token")
                if (response.isSuccessful) {
                    response.body()?.let { walletResponse ->
                        val totalAmount = walletResponse.data.sumOf { it.amountget }
                        amounttotal = totalAmount
                        sharedpreferences.setWalletValue(totalAmount)
                        println("INI WALLETTTT : ${walletResponse.data.sumOf { it.amountget }}")
                    }
                    _responseMessage.value = "Wallet retrieved successfully"
                    println("wallet diambil : ${sharedpreferences.getWalletValue()}")

                } else {
                    _responseMessage.value = "Failed to retrieve wallet: ${response.errorBody()?.string()}"
                }
            } catch (e: Exception) {
                _responseMessage.value = "Exception occurred: ${e.message}"
            }
            println(responseMessage.value)
        }
    }
}




class WalletViewModelFactory(private val sharedpreferences: Sharedpreferences) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(WalletViewModel::class.java)) {
            return WalletViewModel(sharedpreferences) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}


