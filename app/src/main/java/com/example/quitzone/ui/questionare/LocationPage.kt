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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.ungumuda
import com.example.quitzone.viewmodel.proflingViewModel.CitySelectionViewModel


@Composable
fun LocationPage(navController: NavController) {
    val citySelectionViewModel: CitySelectionViewModel = viewModel()
    val selectedCity by citySelectionViewModel.selectedCity
    val filteredCities by citySelectionViewModel.filteredCities

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
            ) {
                StepText(name = "STEP 9/10")
                Spacer(modifier = Modifier.height(72.dp))
                QuestionText(name = "Which province do you live in?")
                Spacer(modifier = Modifier.height(25.dp))

                TextField(
                    value = selectedCity,
                    onValueChange = { citySelectionViewModel.onCityChange(it) },
                    label = {Text("Type the province you live in") },
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(ungumuda, shape = RoundedCornerShape(10.dp))
                )

                Spacer(modifier = Modifier.height(16.dp))

                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(150.dp) // Set a fixed height to make it scrollable when items exceed this height
                ) {
                    items(filteredCities) { city ->
                        Text(
                            text = city,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold,
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    citySelectionViewModel.onCitySelected(city)
                                }
                                .padding(8.dp)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(25.dp))

                DescText(name = "To give you a customized experience we need \n" +
                        "to know your province do you live in")
            }

            Row (horizontalArrangement = Arrangement.SpaceBetween){
                BoxButton(
                    text = "Previous",
                    backgroundColor = Putih, // Assuming Putih is a Color variable
                    textColor = Color.Black
                ) {
                    // Handle the button click
                    println("Previous button clicked!")
                }
                Spacer(modifier = Modifier.width(10.dp))
                BoxButton(
                    text = "Next",
                    backgroundColor = Ungu, // Assuming Putih is a Color variable
                    textColor = Putih
                ) {
                    // Handle the button click
                    println("Next button clicked!")
                }

            }
        }
    }
}