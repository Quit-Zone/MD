package com.example.quitzone.ui.mainfeature

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quitzone.ui.theme.ungulagi

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Diary(navController: NavController) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.TopStart) {
                        Text(
                            text = "Diary",
                            style = TextStyle(
                                color = Color.White,
                                fontSize = 23.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = ungulagi // Use your `ungulagi` color here
                )
            )
        },
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.padding(0.dp),
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                BottomNavigationBar(navController)
            }
        }
    ) {}
}
