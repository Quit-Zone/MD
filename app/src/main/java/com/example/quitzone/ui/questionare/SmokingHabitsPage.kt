package com.example.quitzone.ui.questionare

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.quitzone.ui.theme.Putih
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.desctext
import com.example.quitzone.R
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SmokingHabitsPage() {
    val options = listOf("Frequently", "Occasionally", "Never", "Daily", "More than once a day")
    val selectedOptionIndex = remember { mutableStateOf(2) } // Default to "Never"
    val listState = rememberLazyListState(initialFirstVisibleItemIndex = selectedOptionIndex.value)
    val coroutineScope = rememberCoroutineScope()

    LaunchedEffect(listState) {
        snapshotFlow { listState.firstVisibleItemScrollOffset }
            .collect {
                selectedOptionIndex.value = listState.firstVisibleItemIndex
            }
    }

    Scaffold(modifier = Modifier.padding(15.dp)) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.SpaceBetween,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                StepText(name = "STEP 4/10")
                Spacer(modifier = Modifier.height(72.dp))
                Text(
                    text = "Smoking Habits",
                    style = TextStyle(
                        color = Color.Black,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
                Spacer(modifier = Modifier.height(20.dp))

                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp), // Adjust height as needed
                    contentAlignment = Alignment.Center
                ) {
                    LazyColumn(
                        state = listState,
                        modifier = Modifier.fillMaxWidth(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        itemsIndexed(options) { index, option ->
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(vertical = 8.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    text = option,
                                    style = if (index == selectedOptionIndex.value) {
                                        TextStyle(
                                            color = Color.Black,
                                            fontSize = 18.sp,
                                            fontWeight = FontWeight.SemiBold,
                                            textAlign = TextAlign.Center
                                        )
                                    } else {
                                        TextStyle(
                                            color = desctext,
                                            fontSize = 14.sp,
                                            fontWeight = FontWeight.Normal,
                                            textAlign = TextAlign.Center
                                        )
                                    }
                                )
                            }
                        }
                    }

                    // Center Box for selection indication
                    Box(
                        modifier = Modifier
                            .height(40.dp)
                            .border(2.dp, Ungu, RoundedCornerShape(8.dp))
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    )
                }

                Spacer(modifier = Modifier.height(20.dp))
                Text(
                    text = "To give you a customize experience we need \n" +
                            "to know your smoking habits",
                    style = TextStyle(
                        color = desctext,
                        fontSize = 14.sp,
                        textAlign = TextAlign.Center
                    )
                )
            }

            Row(horizontalArrangement = Arrangement.SpaceBetween) {
                BoxButton(
                    text = "Previous",
                    backgroundColor = Putih, // Assuming Putih is a Color variable
                    textColor = Color.Black
                ) {
                    // Handle the button click
                    if (selectedOptionIndex.value > 0) {
                        coroutineScope.launch {
                            listState.animateScrollToItem(selectedOptionIndex.value - 1)
                        }
                    }
                }
                Spacer(modifier = Modifier.width(10.dp))
                BoxButton(
                    text = "Next",
                    backgroundColor = Ungu, // Assuming Ungu is a Color variable
                    textColor = Putih
                ) {
                    // Handle the button click
                    if (selectedOptionIndex.value < options.size - 1) {
                        coroutineScope.launch {
                            listState.animateScrollToItem(selectedOptionIndex.value + 1)
                        }
                    }
                }
            }
        }
    }
}
