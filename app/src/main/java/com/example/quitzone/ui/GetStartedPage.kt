package com.example.quitzone.ui

import android.media.Image
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
import com.example.quitzone.ui.questionare.BoxButton
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.desctext
import com.example.quitzone.viewmodel.AgeViewModel
import com.example.quitzone.viewmodel.AlcoholConsumptionViewModel
import com.example.quitzone.viewmodel.GenderViewModel
import com.example.quitzone.viewmodel.HeightViewModel
import com.example.quitzone.viewmodel.HobbiesViewModel
import com.example.quitzone.viewmodel.PhysicalActivityViewModel
import com.example.quitzone.viewmodel.ProfileViewModel
import com.example.quitzone.viewmodel.ProfileViewModelFactory
import com.example.quitzone.viewmodel.SmokingHabitsViewModel
import com.example.quitzone.viewmodel.WeightViewModel

@Composable
fun GetStartedPage(
    navController: NavController,
    ageViewModel: AgeViewModel,
    genderViewModel: GenderViewModel,
    smokingHabitsViewModel: SmokingHabitsViewModel,
    physicalActivityViewModel: PhysicalActivityViewModel,
    alcoholConsumptionViewModel: AlcoholConsumptionViewModel,
    hobbiesViewModel: HobbiesViewModel,
    heightViewModel: HeightViewModel,
    weightViewModel: WeightViewModel
) {
    val viewModel: ProfileViewModel = viewModel(
        factory = ProfileViewModelFactory(
            ageViewModel, genderViewModel, smokingHabitsViewModel,
            physicalActivityViewModel, alcoholConsumptionViewModel,
            hobbiesViewModel, heightViewModel, weightViewModel
        )
    )

    val context = LocalContext.current
    val postResult by viewModel.postResult.collectAsState(initial = null)

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
                    color = desctext,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(Modifier.height(40.dp))

            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 49.dp)
                    .background(Ungu, shape = RoundedCornerShape(10.dp))
                    .clickable {
                        viewModel.postProfile()
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

        // Observe postResult and show a toast message on success or failure
        postResult?.let { response ->
            LaunchedEffect(response) {
                if (response.isSuccessful) {
                    Toast.makeText(context, "Profile saved successfully", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(context, "Failed to save profile: ${response.message()}", Toast.LENGTH_SHORT).show()
                    println("Failed to save profile: ${response.message()}")
                }
            }
        }
    }
}
