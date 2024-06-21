import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.ui.mainfeature.BottomNavigationBar
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.R
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.ui.theme.background
import com.example.quitzone.ui.theme.ungulagi
import com.example.quitzone.viewmodel.mainfeatureViewModel.WalletViewModel
import com.example.quitzone.viewmodel.mainfeatureViewModel.WalletViewModelFactory
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.text.NumberFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Calendar
import java.util.Locale

@RequiresApi(Build.VERSION_CODES.O)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Home(navController: NavController) {
    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)

    var username by remember { mutableStateOf("") }
    username = sharedpreferences.getUserName().toString()

    val walletViewModel : WalletViewModel = viewModel(
        factory = WalletViewModelFactory(sharedpreferences)
    )

    var walletAmount by remember { mutableStateOf(0.0) }
    walletAmount = walletViewModel.amounttotal

    // Determine if it's day or night
    val calendar = Calendar.getInstance()
    val hourOfDay = calendar.get(Calendar.HOUR_OF_DAY)
    val isDayTime = hourOfDay in 6..18

    // Get the current date
    val currentDate = LocalDate.now()
    val dateFormatter = DateTimeFormatter.ofPattern("EEEE, dd MMM")
    val formattedDate = currentDate.format(dateFormatter).uppercase()


    Scaffold(
        modifier = Modifier.background(Color.White).padding(top = 10.dp),
        containerColor = Color.White,
        topBar = {
            TopAppBar(
                title = {
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(horizontal = 15.dp),
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
                modifier = Modifier.padding(0.dp).shadow(15.dp),
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                BottomNavigationBar(navController)
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Column(modifier = Modifier.padding(top = 15.dp, start = 30.dp, end = 30.dp)) {
                Box(
                    modifier = Modifier.background(
                        color = Color(0x0F1E1E1E),
                        shape = RoundedCornerShape(20.dp)
                    ).fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(vertical = 22.dp, horizontal = 32.dp)) {
                        Text(
                            text = "Welcome To QuitZone!",
                            style = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold
                            )
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                        Text(
                            text = "Take your time to explore many new things in this app! you can see the community tabs to see the community you’re interested with! we also have some cool features waiting to find out!",
                            style = TextStyle(
                                fontSize = 13.sp,
                                lineHeight = 19.5.sp,

                                fontWeight = FontWeight(400),
                                color = Color.Black,

                                textAlign = TextAlign.Justify,
                            )
                        )
                    }
                }
                Spacer(modifier = Modifier.height(17.dp))
                Text(
                    text = "Wallet",
                    style = TextStyle(
                        fontSize = 18.sp,
                        lineHeight = 18.sp,
                        fontWeight = FontWeight(600),
                        color = Color(0xFF000000),
                    )
                )
                Spacer(modifier = Modifier.height(17.dp))
                Box(
                    modifier = Modifier.background(
                        color = Color(0xFF7065E3),
                        shape = RoundedCornerShape(20.dp)
                    ).fillMaxWidth()
                ) {
                    Row(
                        modifier = Modifier.padding(vertical = 40.dp, horizontal = 25.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            painter = painterResource(
                                id = R.drawable.ic_wallet
                            ),
                            contentDescription = "wallet",
                            tint = Color.White,
                            modifier = Modifier.size(82.dp)
                        )
                        Spacer(modifier = Modifier.width(15.dp))
                        Column() {
                            Text(
                                text = "IDR ",
                                style = TextStyle(
                                    fontSize = 25.sp,
                                    lineHeight = 35.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Justify,
                                    letterSpacing = 0.75.sp,
                                )
                            )
                            Text(
                                text =
                                formatCurrency(sharedpreferences.getLocalWalletValue()),
                                style = TextStyle(
                                    fontSize = 30.sp,
                                    lineHeight = 42.sp,
                                    fontWeight = FontWeight(600),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.Justify,
                                    letterSpacing = 0.9.sp,
                                )
                            )
                        }
                    }
                    Row(
                        modifier = Modifier.padding(vertical = 20.dp, horizontal = 25.dp)
                            .align(Alignment.TopEnd), horizontalArrangement = Arrangement.End
                    ) {
                        Column(
                            verticalArrangement = Arrangement.Top,
                            horizontalAlignment = Alignment.End
                        ) {
                            Text(
                                text = "Your Savings",
                                style = TextStyle(
                                    fontSize = 17.sp,
                                    fontWeight = FontWeight(500),
                                    color = Color(0xFFFFFFFF),
                                    textAlign = TextAlign.End
                                )
                            )
                        }
                    }
                }
                Spacer(modifier = Modifier.height(17.dp))
                Box(
                    modifier = Modifier
                        .background(color = Color(0xFFB58EFF), shape = RoundedCornerShape(20.dp))
                        .fillMaxWidth()
                ) {
                    Column(modifier = Modifier.padding(vertical = 22.dp, horizontal = 32.dp)) {
                        Text(
                            text = "How much the cigarette price you haven’t smoke today?",
                            style = TextStyle(
                                fontSize = 20.sp,
                                lineHeight = 21.sp,
                                fontWeight = FontWeight.W600,
                                color = Color(0xFFFFFFFF),
                            )
                        )
                        Spacer(modifier = Modifier.height(15.dp))
                        BasicTextField(
                            value = walletViewModel.text.value,
                            onValueChange = { walletViewModel.onTextChanged(it) },
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(40.dp)
                                .background(
                                    color = Color(0xFFC8C2FF),
                                    shape = RoundedCornerShape(size = 10.dp)
                                )
                                .padding(
                                    horizontal = 8.dp,
                                    vertical = 10.dp
                                ),  // Adjust padding as needed
                            textStyle = TextStyle(
                                color = Color.Black,
                                fontSize = 18.sp,
                                fontWeight = FontWeight.SemiBold,
                                textAlign = TextAlign.Center
                            ),
                            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                            singleLine = true
                        )
                        Spacer(modifier = Modifier.height(10.dp))

                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.End
                        ) {
                            Column(horizontalAlignment = Alignment.End) {
                                Box(
                                    modifier = Modifier
                                        .width(114.dp)
                                        .height(36.dp)
                                        .clickable {
                                            // Call postWallet when the OK button is clicked
//                                            walletViewModel.postWallet(sharedpreferences.getUserToken().toString())
//                                            walletViewModel.getWallet(sharedpreferences.getUserToken().toString())
                                            walletViewModel.walletLocalPost()
                                            walletViewModel.clearText()
                                        }
                                        .background(
                                            color = Color(0xFF7065E3),
                                            shape = RoundedCornerShape(size = 10.dp)
                                        ),
                                    contentAlignment = Alignment.Center
                                ) {
                                    Text(
                                        text = "OK",
                                        style = TextStyle(
                                            fontSize = 20.sp,
                                            lineHeight = 24.sp,
                                            fontWeight = FontWeight.W500,
                                            color = Color(0xFFFFFFFF),
                                            textAlign = TextAlign.Center,
                                        )
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


fun formatCurrency(amount: Int): String {
    val formatter = NumberFormat.getInstance(Locale("id", "ID"))
    return formatter.format(amount)
}
