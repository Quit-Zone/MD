import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
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
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quitzone.ui.mainfeature.BottomNavigationBar
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.R
import com.example.quitzone.ui.theme.ungulagi
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    var username by remember { mutableStateOf("") }
    username = "Kak Biyyu"

    // Determine if it's day or night
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    val isDayTime = hourOfDay in 6..18

    // Get the current date
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM")
    val formattedDate = currentDate.format(dateFormatter).uppercase()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Column (
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.Start){

                    }
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(15.dp),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Column(horizontalAlignment = Alignment.Start) {
                            Row(verticalAlignment = Alignment.CenterVertically){
                                Icon(
                                    painter = painterResource(
                                        id = if (isDayTime) R.drawable.ic_sun else R.drawable.ic_moon
                                    ),
                                    contentDescription = if (isDayTime) "Sun Icon" else "Moon Icon",
                                    tint = Ungu,
                                    modifier = Modifier.size(13.dp)
                                )
                                Spacer(modifier = Modifier.width(5.dp))
                                Text(
                                    text = formattedDate,
                                    style = TextStyle(
                                        color = Ungu,
                                        fontSize = 13.sp,
                                        fontWeight = FontWeight.Bold
                                    )
                                )
                            }
                            Text(
                                text = "Hi, $username",
                                style = TextStyle(
                                    color = Color.Black,
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.SemiBold
                                )
                            )
                        }
                        Box(modifier = Modifier.size(47.dp).background(Color.LightGray, shape = RoundedCornerShape(10.dp))){
                            Icon(
                                painter = painterResource(
                                    id = R.drawable.ic_person
                                ),
                                contentDescription = "person",
                                tint = Color.White,
                                modifier = Modifier.size(47.dp)
                            )
                        }
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = Color.White // Use your `ungulagi` color here
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
    ) {
        // Main content of the Home screen
    }
}

