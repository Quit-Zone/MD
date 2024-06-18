package com.example.quitzone.ui.questionare

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.ungumuda
import com.example.quitzone.profilingViewModel.SmokingHabitsViewModel

@Composable
fun SmokingHabitsPage(navController: NavController) {
    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)
    val smokingHabitsViewModel: SmokingHabitsViewModel = viewModel()
    val selectedHabit by smokingHabitsViewModel.selectedHabit
    val smokingHabits = listOf("Never", "Occasionally", "Frequently", "Daily", "More than once a day")

    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {


            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier.fillMaxWidth()
            ) {
                StepText(name = "STEP 3/10")
                Spacer(modifier = Modifier.height(72.dp))
                QuestionText(name = "Smoking Habits")
                Spacer(modifier = Modifier.height(25.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp) // Set a fixed height to make it scrollable when items exceed this height
                ) {
                    items(smokingHabits) { habit ->
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clip(RoundedCornerShape(10.dp))
                                .background(if (selectedHabit == habit) ungumuda else Color.Transparent, shape = RoundedCornerShape(10.dp))
                                .clickable {
                                    smokingHabitsViewModel.onHabitSelected(habit)
                                }
                                .padding(8.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = habit,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (selectedHabit == habit) Putih else Color.Black
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))

                DescText(name = "To give you a customized experience we need \n" +
                        "to know your smoking habits")
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween){
                BoxButton(
                    text = "Previous",
                    backgroundColor = Putih, // Assuming Putih is a Color variable
                    textColor = Color.Black
                ) {
                    navController.navigate("genderpage")
                    println("Previous button clicked!")
                }
                Spacer(modifier = Modifier.width(10.dp))
                BoxButton(
                    text = "Next",
                    backgroundColor = Ungu, // Assuming Putih is a Color variable
                    textColor = Putih
                ) {

                    navController.navigate("alcoholconsumption")
                    sharedpreferences.setSmokingHabit(smokingHabitsViewModel.selectedHabit.value)
                    println("Next button clicked! Selected smoking habits: ${smokingHabitsViewModel.selectedHabit.value}")
                    println("Next button clicked!")
                }

            }
        }
    }
}
