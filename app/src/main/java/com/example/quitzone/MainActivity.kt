package com.example.quitzone

import Home
import LogIn
import SignUp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.ui.input.key.Key.Companion.Home
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quitzone.ui.GetStartedPage
import com.example.quitzone.ui.mainfeature.BottomNavigationBar
import com.example.quitzone.ui.mainfeature.Community
import com.example.quitzone.ui.mainfeature.CommunityViewModel
import com.example.quitzone.ui.mainfeature.Diary
import com.example.quitzone.ui.questionare.AgePage
import com.example.quitzone.ui.questionare.AlcoholConsumptionPage
import com.example.quitzone.ui.questionare.CigarretesPricePage
import com.example.quitzone.ui.questionare.GenderPage
import com.example.quitzone.ui.questionare.HeightPage
import com.example.quitzone.ui.questionare.HobbyPage
import com.example.quitzone.ui.questionare.LocationPage
import com.example.quitzone.ui.questionare.PhysicalActivityPage
import com.example.quitzone.ui.questionare.SmokingHabitsPage
import com.example.quitzone.ui.questionare.WeightPage
import com.example.quitzone.ui.theme.QuitZoneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitZoneTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "home") {
                    composable("login") { LogIn(navController) }
                    composable("signup") { SignUp(navController) }
                    //1
                    composable("agepage") { AgePage(navController) }
                    //2
                    composable("genderpage") { GenderPage(navController) }
                    //3
                    composable("smokinghabits") { SmokingHabitsPage(navController) }
                    //4
                    composable("alcoholconsumption") { AlcoholConsumptionPage(navController) }
                    //5
                    composable("physicalactivity") { PhysicalActivityPage(navController) }
                    //6
                    composable("hobbypage") { HobbyPage(navController) }
                    //7
                    composable("heightpage") { HeightPage(navController) }
                    //8
                    composable("weightpage") { WeightPage(navController) }
                    //9
                    composable("locationpage") { LocationPage(navController) }
                    //10
                    composable("cigarettepricepage") { CigarretesPricePage(navController) }
                    //11
                    composable("getstartedpage") { GetStartedPage(navController)
                    }
                    composable("community"){ Community(navController) }
                    composable("diary"){(Diary(navController))}
                    composable("home"){(Home(navController))}
                }
            }
        }
    }
}






