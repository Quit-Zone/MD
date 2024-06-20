package com.example.quitzone.ui.mainfeature

import android.annotation.SuppressLint
import android.media.audiofx.AudioEffect.Descriptor
import android.provider.CalendarContract
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.quitzone.R
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.ungulagi
import com.example.quitzone.ui.theme.ungulagilagi



@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Diary(navController: NavController) {
    Scaffold(
        bottomBar = {
            BottomAppBar(
                modifier = Modifier.padding(0.dp).shadow(15.dp),
                containerColor = Color.White,
                contentColor = Color.Black
            ) {
                BottomNavigationBar(navController)
            }
        },
        containerColor = ungulagi
    ) {
        Column {
            Box(modifier = Modifier.background(ungulagi).fillMaxWidth().padding(top = 50.dp , bottom = 20.dp), contentAlignment = Alignment.Center) {
                Column (horizontalAlignment = Alignment.CenterHorizontally){
                    Text(
                        text = "Daily Diary",
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 23.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Keeping a diary is a great way \nto track your progress with cravings",
                        style = TextStyle(
                            fontSize = 14.sp,
                            fontWeight = FontWeight(400),
                            color = Color(0xFFFFFFFF),

                            textAlign = TextAlign.Center,
                        )
                    )
                    Spacer(Modifier.height(25.dp))
                    NewDiaryButton {
                        navController.navigate("newDiaryEntry")
                    }
                    Spacer(Modifier.height(25.dp))
                }

            }
            Column(
                modifier = Modifier
                    .padding().height(750.dp)
                    .background(Color.White, RoundedCornerShape(topStart = 24.dp, topEnd = 24.dp)), // Set the same background color as Scaffold if needed
            ) {
                Column(
                    modifier = Modifier
                        .padding(start = 30.dp, top = 30.dp, end = 30.dp)
                        .fillMaxSize(),
                    verticalArrangement = Arrangement.Top,
                ) {
                    DiaryEntryCard(entry = DiaryEntry("17 Dec 2023", R.drawable.ic_happy, 3, 9))
                }
            }
        }
    }
}

@Composable
fun DiaryEntryCard(entry: DiaryEntry) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        shape = RoundedCornerShape(10.dp),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(15.dp)
        ) {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "17",
                    style = TextStyle(
                        fontSize = 42.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF7065E3),
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = "Dec 2023",
                    style = TextStyle(
                        fontSize = 16.sp,
                        lineHeight = 24.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Status",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Center,
                    )
                )
                Icon(
                    painter = painterResource(id = entry.statusIcon),
                    contentDescription = "Status Icon",
                    modifier = Modifier.size(44.dp),
                    tint = Color.Unspecified // This removes the default black tint
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Cravings",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = entry.cravings.toString(),
                    style = TextStyle(
                        fontSize = 34.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF7065E3),
                        textAlign = TextAlign.Center,
                    )
                )
            }
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    text = "Severity",
                    style = TextStyle(
                        fontSize = 14.sp,
                        fontWeight = FontWeight.W400,
                        color = Color(0xFF1E1E1E),
                        textAlign = TextAlign.Center,
                    )
                )
                Text(
                    text = entry.severity.toString(),
                    style = TextStyle(
                        fontSize = 34.sp,
                        lineHeight = 51.sp,
                        fontWeight = FontWeight.W500,
                        color = Color(0xFF7065E3),
                        textAlign = TextAlign.Center,
                    )
                )
            }
        }
    }
}


@Composable
fun NewDiaryButton(onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .background(color = Color(0xFFFFFFFF), shape = RoundedCornerShape(10.dp))
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center
    ) {
        Row(modifier = Modifier.padding(vertical = 12.dp, horizontal = 49.dp)){
            Text(
                text = "New Entry",
                style = TextStyle(
                    fontSize = 14.sp,
                    lineHeight = 21.sp,
                    fontWeight = FontWeight(600),
                    color = Color(0xFF7065E3),
                    textAlign = TextAlign.Justify,
                )
            )
        }

    }
}

data class DiaryEntry(
    val date: String,
    val statusIcon: Int,
    val cravings: Int,
    val severity: Int
)

val dummyDiaryEntries = listOf(
    DiaryEntry("17 Dec 2023", R.drawable.ic_person, 3, 9),
    DiaryEntry("16 Dec 2023", R.drawable.ic_person, 0, 2),
    DiaryEntry("15 Dec 2023", R.drawable.ic_person, 1, 3)
)


