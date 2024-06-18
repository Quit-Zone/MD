package com.example.quitzone.ui

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.R
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.retrofit.RetrofitInstance
import com.example.quitzone.profilingViewModel.ProfileViewModel
import com.example.quitzone.profilingViewModel.ProfileViewModelFactory

@Composable
fun GetStartedPage(navController: NavController) {
    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)

    // Provide ApiService instance
    val apiService = RetrofitInstance.api

    // Create ViewModel using the factory
    val viewModelFactory = ProfileViewModelFactory(
        // Pass dependencies here
        apiService = apiService,
        sharedpreferences = sharedpreferences
    )
    val profileViewModel: ProfileViewModel = viewModel(
        factory = viewModelFactory
    )

    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Image(
                painter = painterResource(id = R.drawable.lung_image),
                contentDescription = "Gambar",
                modifier = Modifier.size(width = 250.dp, height = 250.dp) // Adjust size as needed
            )
            Spacer(Modifier.height(20.dp))
            Text(
                text = "You are ready to go",
                style = TextStyle(
                    color = Color.Black,
                    fontSize = 23.sp,
                    fontWeight = FontWeight.Bold
                )
            )
            Spacer(Modifier.height(10.dp))
            Text(
                text = "Thanks for your time to personalize\n" +
                        "your profile with us. Now this is the \n" +
                        "fun part, let’s explore the app.",
                style = TextStyle(
                    color = Color.Gray,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 49.dp)
                    .background(Color.Green, shape = RoundedCornerShape(10.dp))
                    .clickable {
                        // Post the profile using ProfileViewModel
                        val Token = sharedpreferences.getUserToken()
                        println("token ${Token}")
                        profileViewModel.postProfile(Token.toString())
                    },
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Get Started!",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }

    // Observe the result of the profile post
    val postResult by profileViewModel.profilePostResult.collectAsState()

    LaunchedEffect(postResult) {
        postResult?.let {
            it.onSuccess {
                // Show success toast
                Toast.makeText(context, "Profile successfully added!", Toast.LENGTH_LONG).show()
                // Handle success, e.g., navigate to another screen
                navController.navigate("nextScreenRoute")
            }.onFailure { exception ->
                // Handle error, e.g., show a toast message
                Toast.makeText(context, "Error: ${exception.message}", Toast.LENGTH_LONG).show()
                println("Error: ${exception.message}")
            }
        }
    }
}




