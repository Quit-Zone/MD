package com.example.quitzone.ui.mainfeature

import android.annotation.SuppressLint
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.R
import com.example.quitzone.ui.theme.ungulagi
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.O)
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewDiaryEntryScreen(navController: NavController, viewModel: DiaryViewModel = viewModel()) {
    val currentDate = LocalDate.now().format(DateTimeFormatter.ofPattern("dd MMM yyyy"))
    var date by remember { mutableStateOf(currentDate) }
    var cravings by remember { mutableStateOf(0) }
    var severity by remember { mutableStateOf(0) }
    var statusIcon by remember { mutableStateOf(R.drawable.ic_person) } // Default icon

    Scaffold(
        containerColor = ungulagi
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .background(ungulagi)
                    .fillMaxWidth()
                    .padding(top = 50.dp, bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(
                    text = "Add Diary",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
            }
            Column(
                modifier = Modifier
                    .fillMaxHeight().fillMaxWidth()
                    .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp))
                    .padding(30.dp),
                verticalArrangement = Arrangement.Top,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = "How Do You Feel Today?",
                        style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF1E1E1E),
                        )
                    )
                    Text(
                        text = date,
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(600),
                            color = Color(0xFF7065E3),
                        )
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                ValueAdjuster(
                    value = cravings,
                    onIncrease = { cravings++ },
                    onDecrease = { if (cravings > 0) cravings-- },
                    label = "Cravings"
                )
                Spacer(modifier = Modifier.height(16.dp))
                ValueAdjuster(
                    value = severity,
                    onIncrease = { severity++ },
                    onDecrease = { if (severity > 0) severity-- },
                    label = "Severity"
                )
                Spacer(modifier = Modifier.height(16.dp))
                SaveDiaryButton {
                    val newEntry = DiaryEntry(date, statusIcon, cravings, severity)
                    viewModel.addDiaryEntry(newEntry)
                    navController.popBackStack()
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ValueAdjuster(
    value: Int,
    onIncrease: () -> Unit,
    onDecrease: () -> Unit,
    label: String
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = label,
            style = TextStyle(
                fontSize = 16.sp,
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                textAlign = TextAlign.Center
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Row(
            verticalAlignment = Alignment.CenterVertically,
        ) {
            // Button to decrease value
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ungulagi)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
                    .clickable { onDecrease() }
            ) {
                Text(
                    text = "-",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            // Value TextField
            Box(
                modifier = Modifier
                    .width(60.dp)
                    .padding(top = 8.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(Color.LightGray)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
            ) {
                TextField(
                    value = value.toString(),
                    onValueChange = { /* No-op: value change is handled by buttons */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent),
                    textStyle = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold,
                        textAlign = TextAlign.Center
                    ),
                    colors = TextFieldDefaults.textFieldColors(
                        focusedIndicatorColor = Color.Transparent, // Hide the focused border
                        unfocusedIndicatorColor = Color.Transparent // Hide the unfocused border
                    ),
                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
                    singleLine = true,
                    readOnly = true
                )
            }
            Spacer(modifier = Modifier.width(10.dp))
            // Button to increase value
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(10.dp))
                    .background(ungulagi)
                    .border(1.dp, Color.Transparent, RoundedCornerShape(10.dp))
                    .clickable { onIncrease() }
            ) {
                Text(
                    text = "+",
                    color = Color.White,
                    fontSize = 16.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
fun SaveDiaryButton(onClick: () -> Unit){
    Box(
        modifier = Modifier
            .background(color = ungulagi, shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick).fillMaxWidth(),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 49.dp)){
            Text(
                text = "Save Entry",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(600),
                    color = Color.White,
                    textAlign = TextAlign.Justify,
                )
            )
        }
    }
}
