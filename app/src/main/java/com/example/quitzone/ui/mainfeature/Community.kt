package com.example.quitzone.ui.mainfeature

import android.annotation.SuppressLint
import android.media.audiofx.AudioEffect.Descriptor
import android.text.style.BackgroundColorSpan
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.quitzone.R
import com.example.quitzone.preferences.Sharedpreferences
import com.example.quitzone.ui.theme.Ungu
import com.example.quitzone.ui.theme.ungulagi
import com.example.quitzone.ui.theme.ungulagilagi
import com.example.quitzone.viewmodel.mainfeatureViewModel.PredictionViewModel
import com.example.quitzone.viewmodel.mainfeatureViewModel.PredictionViewModelFactory


@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Community(navController: NavController, viewModel: CommunityViewModel = androidx.lifecycle.viewmodel.compose.viewModel()) {
    val context = LocalContext.current
    val sharedpreferences = Sharedpreferences(context)

    val communityItems by viewModel.communityItems.observeAsState(emptyList())
    val communityCategories by viewModel.communityCategories.observeAsState(emptyList())
    val selectedItem by viewModel.selectedItem.observeAsState("All")
    val filteredItems by viewModel.filteredItems.observeAsState(emptyList())


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
        Column(Modifier.padding(bottom = 80.dp)) {
            Box(modifier = Modifier.background(ungulagi).fillMaxWidth().padding(top = 50.dp , bottom = 20.dp), contentAlignment = Alignment.Center) {
                Text(
                    text = "Community",
                    style = TextStyle(
                        color = Color.White,
                        fontSize = 23.sp,
                        fontWeight = FontWeight.SemiBold
                    )
                )
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
                    Text(
                        text = "Recommended Community",
                        style = TextStyle(
                            color = Color.Black,
                            fontSize = 18.sp,
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyRow(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.spacedBy(0.dp)
                    ) {
                        val recommendedItems = communityItems.filter { it.category == sharedpreferences.getPredictionValue()}
                        items(recommendedItems.size) { index ->
                            CommunityTiles(
                                icon = recommendedItems[index].icon,
                                title = recommendedItems[index].title
                            )
                        }
                    }
                    Spacer(modifier = Modifier.height(16.dp))
                    CommunityButtonRow(
                        communityItems = communityCategories,
                        selectedItem = selectedItem,
                        onItemSelected = { viewModel.selectCategory(it) }
                    )
                    Spacer(modifier = Modifier.height(16.dp))
                    LazyColumn(
                        modifier = Modifier.fillMaxWidth(),
                        verticalArrangement = Arrangement.spacedBy(15.dp)
                    ) {
                        items(filteredItems.size) { index ->
                            CommunityCard(
                                item = filteredItems[index]
                            )
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun CommunityTiles(icon: Int, title: String) {
    Column(
        modifier = Modifier
            .padding(8.dp)
            .size(150.dp)
            .background(ungulagi, shape = RoundedCornerShape(20.dp)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Icon(
            painter = painterResource(id = icon),
            contentDescription = title,
            tint = Color.White,
            modifier = Modifier.size(80.dp)
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = title,
            color = Color.White,
            fontWeight = FontWeight.Bold
        )
    }
}

@Composable
fun CommunityButton(item: CommunityCategory, isSelected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .padding(horizontal = 4.dp)
            .background(
                color = if (isSelected) Color(0xFF7B61FF) else Color(0xFFE5DFF8),
                shape = RoundedCornerShape(50)
            )
            .clickable(onClick = onClick)
            .padding(vertical = 8.dp, horizontal = 16.dp)
    ) {
        Text(
            text = item.title,
            textAlign = TextAlign.Center,
            color = if (isSelected) Color.White else Color.White
        )
    }
}

@Composable
fun CommunityButtonRow(communityItems: List<CommunityCategory>, selectedItem: String, onItemSelected: (String) -> Unit) {
    LazyRow(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.spacedBy(0.dp)
    ) {
        items(communityItems) { item ->
            CommunityButton(
                item = item,
                isSelected = item.title == selectedItem,
                onClick = { onItemSelected(item.title) }
            )
        }
    }
}

@Composable
fun CommunityCard(item: CommunityItem) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(ungulagi, shape = RoundedCornerShape(10.dp)), // Add padding inside the card content
        verticalAlignment = Alignment.Bottom,
    ) {
        Box(modifier = Modifier
            .fillMaxWidth()
            .height(130.dp)
            .background(ungulagilagi, shape = RoundedCornerShape(bottomStart = 10.dp, bottomEnd = 10.dp))
            .padding(20.dp), // Add padding inside the card content
        ){
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
                    .height(140.dp),

                verticalAlignment = Alignment.CenterVertically// Add padding inside the card content
            ){
                Icon(
                    painter = painterResource(id = item.icon),
                    contentDescription = item.title,
                    tint = Color.White,
                    modifier = Modifier.size(70.dp) // Adjust icon size to match the image
                )
                Spacer(modifier = Modifier.width(16.dp)) // Adjust the spacer width to separate icon and text
                Column {
                    Text(
                        text = item.title,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 18.sp, // Adjust font size to match the title text size in the image
                            fontWeight = FontWeight.SemiBold
                        )
                    )
                    Spacer(Modifier.height(4.dp)) // Adjust space between title and description
                    Text(
                        text = item.description,
                        style = TextStyle(
                            color = Color.White,
                            fontSize = 14.sp, // Adjust font size to match the description text size in the image
                            fontWeight = FontWeight.Normal
                        )
                    )
                }
            }
        }
    }
}
