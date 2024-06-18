package com.example.quitzone.ui.questionare
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.desctext
import com.example.quitzone.viewmodel.proflingViewModel.WeightViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun WeightPage(navController: NavController) {
    val viewModel: WeightViewModel = viewModel()
    val weight by viewModel.weight

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
                StepText(name = "STEP 8/10")
                Spacer(modifier = Modifier.height(72.dp))
                QuestionText(name = "What’s your weight?")
                Spacer(modifier = Modifier.height(25.dp))
                Row(
                    verticalAlignment = Alignment.Bottom,
                    horizontalArrangement = Arrangement.Center
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp)
                            .padding(top = 8.dp)
                            .clip(RoundedCornerShape(10.dp))
                            .background(Color.LightGray)
                            .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
                    ) {
                        TextField(
                            value = weight,
                            onValueChange = { newWeight -> viewModel.updateWeight(newWeight) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(Color.Transparent),
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ),
                            colors = TextFieldDefaults.textFieldColors(
                                focusedIndicatorColor = Color.Transparent, // Hide the focused border
                                unfocusedIndicatorColor = Color.Transparent // Hide the unfocused border
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(
                                keyboardType = KeyboardType.Number
                            )
                        )
                    }
                    Spacer(modifier = Modifier.width(10.dp))
                    Column(
                        verticalArrangement = Arrangement.Bottom
                    ) {
                        Text(
                            text = "kg",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                textAlign = TextAlign.Center
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "To give you a customized experience \n" +
                            "we need to know what is your weight",
                    style = TextStyle(
                        color = desctext,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                BoxButton(
                    text = "Previous",
                    backgroundColor = Putih, // Assuming Putih is a Color variable
                    textColor = Color.Black
                ) {
                    navController.navigate("heightpage")
                    println("Previous button clicked!")
                }
                Spacer(modifier = Modifier.width(10.dp))
                BoxButton(
                    text = "Next",
                    backgroundColor = Ungu, // Assuming Ungu is a Color variable
                    textColor = Putih
                ) {
                    navController.navigate("getstartedpage")
                    val weightAsFloat = viewModel.getWeightAsFloat()
                    println("Next button clicked! Weight: $weightAsFloat kg")
                }
            }
        }
    }
}


