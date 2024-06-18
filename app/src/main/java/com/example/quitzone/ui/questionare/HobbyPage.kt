package com.example.quitzone.ui.questionare

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.viewmodel.proflingViewModel.HobbiesViewModel
import com.example.quitzone.profilingViewModel.HobbiesViewModel
import kotlinx.coroutines.launch

@Composable
fun HobbyPage(navController: NavController) {
    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)
    val viewModel: HobbiesViewModel = viewModel()
    val selectedHobbies by viewModel.selectedHobbies.collectAsState()
    val coroutineScope = rememberCoroutineScope()

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
                StepText(name = "STEP 6/10")
                Spacer(modifier = Modifier.height(72.dp))
                QuestionText(name = "Hobbies")
                Spacer(modifier = Modifier.height(25.dp))
                TextField(
                    value = selectedHobbies.joinToString(", "),
                    onValueChange = {},
                    label = { Text("Choose your hobbies max of three") },
                    readOnly = true,
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                )
                Spacer(modifier = Modifier.height(16.dp))

                val hobbies = listOf(
                    "Running/Jogging", "Badminton", "Swimming", "Fitness/Exercise", "Gardening",
                    "Cooking/Baking", "Writing", "Fishing", "Traveling", "Playing Cards/Board Games",
                    "Reading Books", "Video Gaming", "Volunteering", "Yoga/Meditation"
                )

                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    items(hobbies.size) { index ->
                        val hobby = hobbies[index]
                        val isSelected = hobby in selectedHobbies
                        Box(
                            modifier = Modifier
                                .background(
                                    if (isSelected) Color(0xFF6200EA) else Color(0xFFF0F0F0),
                                    RoundedCornerShape(20.dp)
                                )
                                .clickable {
                                    coroutineScope.launch {
                                        viewModel.toggleHobby(hobby)
                                    }
                                }
                                .padding(horizontal = 10.dp, vertical = 8.dp),
                        ) {
                            Text(
                                text = hobby,
                                color = if (isSelected) Color.White else Color.Black,
                                style = MaterialTheme.typography.bodyLarge,
                                textAlign = TextAlign.Center,
                                modifier = Modifier.fillMaxWidth()
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))
                DescText(name = "To give you a customize experience we need \n" +
                        "to know your hobbies")
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween){
                BoxButton(
                    text = "Previous",
                    backgroundColor = Putih, // Assuming Putih is a Color variable
                    textColor = Color.Black
                ) {
                    navController.navigate("hobbypage")
                    println("Previous button clicked!")
                }
                Spacer(modifier = Modifier.width(10.dp))
                BoxButton(
                    text = "Next",
                    backgroundColor = Ungu, // Assuming Ungu is a Color variable
                    textColor = Putih
                ) {
                    navController.navigate("heightpage")
                    println("Next button clicked! Selected hobbies: ${viewModel.selectedHobbies.value}")

                    val hobbiesList = selectedHobbies.toList()
                    if (hobbiesList.size >= 1) sharedpreferences.setHobby1(hobbiesList[0])
                    if (hobbiesList.size >= 2) sharedpreferences.setHobby2(hobbiesList[1])
                    if (hobbiesList.size >= 3) sharedpreferences.setHobby3(hobbiesList[2])

                    println("Hobbies saved: ${hobbiesList.joinToString(", ")}")
                }
            }
        }
    }
}

