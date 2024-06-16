package com.example.quitzone.ui.questionare

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quitzone.R
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.desctext

@Composable
fun GenderPage(navController: NavController) {
    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {

            Box {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    StepText(name = "STEP 2/10")
                    Spacer(modifier = Modifier.height(72.dp))
                    QuestionText(name = "Which one are you?")
                    Spacer(modifier = Modifier.height(25.dp))
                    Row(horizontalArrangement = Arrangement.SpaceBetween) {
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val backgroundColor =
                                remember { mutableStateOf(Ungu) } // Assuming Ungu is a Color variable

                            BoxGender(
                                backgroundColor = backgroundColor,
                                imageResource = R.drawable.man
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            QuestionText(name = "Man")
                        }
                        Spacer(modifier = Modifier.width(10.dp))
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            val backgroundColor =
                                remember { mutableStateOf(Putih) } // Assuming Ungu is a Color variable

                            BoxGender(
                                backgroundColor = backgroundColor,
                                imageResource = R.drawable.women
                            )
                            Spacer(modifier = Modifier.height(3.dp))
                            QuestionText(name = "Women")
                        }
                    }
                    Spacer(modifier = Modifier.height(25.dp))
                    DescText(
                        name = "To give you a customize experience \n" +
                                "we need to know your gender"
                    )
                }
            }
            Row(horizontalArrangement = Arrangement.SpaceBetween) {
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

@Composable
fun StepText(name: String) {
    Text(
        text = name,
        style = TextStyle(
            color = Ungu,
            fontSize = 15.sp,
        )
    )
}

@Composable
fun QuestionText(name: String) {
    Text(
        text = name,
        fontSize = 18.sp,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun DescText(name: String) {
    Text(
        text = name,
        fontSize = 14.sp,
        color = desctext,
        textAlign = TextAlign.Center
    )
}


@Composable
fun BoxGender(
    backgroundColor: MutableState<Color>, // Use a mutable state to track the background color
    imageResource: Int
) {
    val originalColor = remember { backgroundColor.value }

    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 202.dp)
            .background(backgroundColor.value, shape = RoundedCornerShape(20.dp))
            .clickable { // Make the box clickable
                // Toggle background color between original color and Magenta
                backgroundColor.value = if (backgroundColor.value == originalColor) {
                    Color.Magenta
                } else {
                    originalColor
                }
            }
    ) {
        Image(
            painter = painterResource(id = imageResource),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier.fillMaxSize()
        )
    }
}


@Composable
fun BoxButton(
    text: String,
    backgroundColor: Color,
    textColor: Color,
    onClick: () -> Unit = {} // Add a default empty lambda for onClick
) {
    Box(
        modifier = Modifier
            .size(width = 166.dp, height = 49.dp)
            .background(backgroundColor, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick), // Add the clickable modifier
        contentAlignment = Alignment.Center
    ) {
        Text(
            text = text,
            fontSize = 14.sp,
            color = textColor
        )
    }
}




