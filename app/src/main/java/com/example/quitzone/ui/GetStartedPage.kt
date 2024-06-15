package com.example.quitzone.ui

import android.media.Image
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quitzone.R
import com.example.quitzone.ui.questionare.BoxButton
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.desctext

@Composable
fun GetStartedPage(){
    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ){
            Image(
                painter = painterResource(id = R.drawable.lung_image),
                contentDescription = "Gambar",
                modifier = Modifier.size(width = 250.dp, height = 250.dp) // Sesuaikan ukuran sesuai kebutuhan
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
                text = "Thanks for your time to personalized\n" +
                        "your profile with us. Now this is the \n" +
                        "fun part, let’s explore the app.",
                style = TextStyle(
                    color = desctext,
                    fontSize = 14.sp,
                    textAlign = TextAlign.Center
                )
            )
            Spacer(Modifier.height(40.dp))

            val onClick:() -> Unit = {

            }
            Box(
                modifier = Modifier
                    .size(width = 250.dp, height = 49.dp)
                    .background(Ungu, shape = RoundedCornerShape(10.dp))
                    .clickable(onClick = onClick), // Add the clickable modifier
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Get Started!",
                    fontSize = 14.sp,
                    color = Color.White
                )
            }

        }}
}