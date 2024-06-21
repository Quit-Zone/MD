package com.example.quitzone

import Home
import LogIn
import SignUp
import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.quitzone.ui.GetStartedPage
import com.example.quitzone.ui.mainfeature.BottomNavigationBar
import com.example.quitzone.ui.mainfeature.Community
import com.example.quitzone.ui.mainfeature.CommunityViewModel
import com.example.quitzone.ui.mainfeature.Diary
import com.example.quitzone.ui.mainfeature.NewDiaryEntryScreen
import com.example.quitzone.ui.questionare.*
import com.example.quitzone.ui.theme.QuitZoneTheme
import kotlinx.coroutines.delay

class MainActivity : ComponentActivity() {
    @SuppressLint("NewApi")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            QuitZoneTheme {
                val navController = rememberNavController()

                NavHost(navController, startDestination = "splash") {
                    composable("splash") { SplashScreen(navController) }
                    composable("login") { LogIn(navController) }
                    composable("signup") { SignUp(navController) }
                    composable("agepage") { AgePage(navController) }
                    composable("genderpage") { GenderPage(navController) }
                    composable("smokinghabits") { SmokingHabitsPage(navController) }
                    composable("alcoholconsumption") { AlcoholConsumptionPage(navController) }
                    composable("physicalactivity") { PhysicalActivityPage(navController) }
                    composable("hobbypage") { HobbyPage(navController) }
                    composable("heightpage") { HeightPage(navController) }
                    composable("weightpage") { WeightPage(navController) }
                    composable("locationpage") { LocationPage(navController) }
                    composable("cigarettepricepage") { CigarretesPricePage(navController) }
                    composable("getstartedpage") { GetStartedPage(navController) }
                    composable("community") { Community(navController) }
                    composable("diary") { Diary(navController) }
                    composable("newDiaryEntry") { NewDiaryEntryScreen(navController) }
                    composable("home") { Home(navController) }
                }
            }
        }
    }
}

@Composable
fun SplashScreen(navController: NavController) {
    LaunchedEffect(Unit) {
        delay(1500) // Durasi splash screen (3 detik)
        navController.navigate("login") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
        contentAlignment = Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.ic_appicon), // Ganti dengan logo aplikasi Anda
            contentDescription = "App Logo",
            contentScale = ContentScale.Crop,
            modifier = Modifier.size(200.dp)
        )
    }
}
