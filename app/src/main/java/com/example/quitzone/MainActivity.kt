package com.example.quitzone

import LogIn
import SignUp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quitzone.ui.questionare.GenderPage
import com.example.quitzone.ui.questionare.HobbyPage
import com.example.quitzone.ui.questionare.LocationPage
import com.example.quitzone.ui.theme.QuitZoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitZoneTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "locationpage") {
                    composable("login") { LogIn(navController) }
                    composable("signup") { SignUp(navController) }
                    composable("genderpage") { GenderPage(navController) }
                    composable("hobbypage") { HobbyPage(navController) }
                    composable("locationpage") { LocationPage(navController) }
                }
            }
        }
    }
}






