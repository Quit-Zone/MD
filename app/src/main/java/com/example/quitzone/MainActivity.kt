package com.example.quitzone

import LogIn
import SignUp
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quitzone.ui.GetStartedPage
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
import com.example.quitzone.viewmodel.AgeViewModel
import com.example.quitzone.viewmodel.AlcoholConsumptionViewModel
import com.example.quitzone.viewmodel.GenderViewModel
import com.example.quitzone.viewmodel.HeightViewModel
import com.example.quitzone.viewmodel.HobbiesViewModel
import com.example.quitzone.viewmodel.PhysicalActivityViewModel
import com.example.quitzone.viewmodel.SmokingHabitsViewModel
import com.example.quitzone.viewmodel.WeightViewModel

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitZoneTheme {
                val navController = rememberNavController()
                NavHost(navController, startDestination = "login") {
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
                    composable("getstartedpage") {
                        val ageViewModel: AgeViewModel = viewModel()
                        val genderViewModel: GenderViewModel = viewModel()
                        val smokingHabitsViewModel: SmokingHabitsViewModel = viewModel()
                        val physicalActivityViewModel: PhysicalActivityViewModel = viewModel()
                        val alcoholConsumptionViewModel: AlcoholConsumptionViewModel = viewModel()
                        val hobbiesViewModel: HobbiesViewModel = viewModel()
                        val heightViewModel: HeightViewModel = viewModel()
                        val weightViewModel: WeightViewModel = viewModel()

                        GetStartedPage(
                            navController = navController,
                            ageViewModel = ageViewModel,
                            genderViewModel = genderViewModel,
                            smokingHabitsViewModel = smokingHabitsViewModel,
                            physicalActivityViewModel = physicalActivityViewModel,
                            alcoholConsumptionViewModel = alcoholConsumptionViewModel,
                            hobbiesViewModel = hobbiesViewModel,
                            heightViewModel = heightViewModel,
                            weightViewModel = weightViewModel
                        )
                    }
                }
            }
        }
    }
}






