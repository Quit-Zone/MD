package com.example.quitzone

import LogIn
import SignUp
import android.os.Bundle
import android.provider.CalendarContract
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quitzone.ui.questionare.GenderPage
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.QuitZoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitZoneTheme {
                LogIn()
            }
        }
    }
}


@Composable
fun StepText(name : String){
    Text(
        text = name,
        style = TextStyle(
            color = Color.Magenta,
            fontSize = 15.sp,

            )
    )
}

@Composable
fun QuestionText(name: String){
    Text(
        text = name,
        fontSize = 20.sp,
        color = Color.Black,
        fontWeight = FontWeight.SemiBold
    )
}

@Composable
fun BoxGender(name: Color) {
    Box(
        modifier = Modifier
            .size(width = 140.dp, height = 202.dp)
            .background(name, shape = RoundedCornerShape(20.dp))
    )
}

@Composable
fun GenderPage(){
    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column (modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            StepText(name = "STEP 1/7")
            QuestionText(name = "Which one are you?")
            Row (horizontalArrangement = Arrangement.SpaceBetween){
                BoxGender(Ungu)
                Spacer(modifier = Modifier.width(16.dp))
                BoxGender(Putih)
            }
        }
    }
}

