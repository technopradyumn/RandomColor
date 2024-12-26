package com.technopradyumn.randomcolor.ui

import android.graphics.Color as AndroidColor
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material.icons.outlined.Sync
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.scale
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.technopradyumn.randomcolor.viewmodel.ColorViewModel
import androidx.hilt.navigation.compose.hiltViewModel
import com.technopradyumn.randomcolor.data.db.ColorEntity
import java.text.SimpleDateFormat
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ColorAppScreen(viewModel: ColorViewModel = hiltViewModel()) {
    val colors by viewModel.colors.collectAsState(initial = emptyList())
    val pendingCount by viewModel.pendingCount.collectAsState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        color = Color.White,
                        text = "Random Color App"
                    )
                },
                colors = TopAppBarDefaults.mediumTopAppBarColors(containerColor = MaterialTheme.colorScheme.primary),
                actions = {
                    Box(
                        modifier = Modifier
                            .padding(start = 6.dp, end = 10.dp)
                            .background(
                                color = Color(0xFFC8b2e3),
                                shape = RoundedCornerShape(50.dp, 50.dp, 50.dp, 50.dp)
                            )
                    ) {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier
                                .clickable {
                                    viewModel.syncColors()
                                    viewModel.updatePendingCount()
                                }
                        ) {
                            Text(
                                modifier = Modifier.padding(start = 15.dp, end = 8.dp),
                                text = "$pendingCount",
                                fontSize = 24.sp,
                                color = Color.White
                            )
                            Icon(
                                Icons.Outlined.Sync,
                                contentDescription = "Sync Colors",
                                modifier = Modifier
                                    .size(48.dp)
                                    .padding(end = 8.dp)
                                    .rotate(120F)
                                    .scale(scaleX = 0.8f, scaleY = 0.8f),
                                tint = MaterialTheme.colorScheme.primary
                            )
                        }
                    }
                }
            )
        },
        floatingActionButton = {
            Box(
                modifier = Modifier
                    .background(
                        color = Color(0xFFC8b2e3),
                        shape = RoundedCornerShape(50)
                    )
                    .clickable { viewModel.addRandomColor() }
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.Center,
                    modifier = Modifier
                        .padding(start = 8.dp, end = 4.dp, top = 4.dp, bottom = 4.dp)
                ) {
                    Text(
                        text = "Add Color",
                        color = MaterialTheme.colorScheme.primary,
                        style = MaterialTheme.typography.bodyMedium
                    )
                    Spacer(modifier = Modifier.width(2.dp))
                    Icon(
                        Icons.Default.AddCircle,
                        contentDescription = "Add Color",
                        modifier = Modifier.size(32.dp),
                        tint = MaterialTheme.colorScheme.primary
                    )
                }
            }
        },
        modifier = Modifier.fillMaxSize()
    ) { innerPadding ->
        Box(modifier = Modifier.padding(innerPadding)) {
            if (colors.isEmpty()) {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Text("No colors added yet!", style = MaterialTheme.typography.bodyLarge)
                }
            } else {
                LazyVerticalGrid(
                    columns = GridCells.Fixed(2),
                    modifier = Modifier.fillMaxSize(),
                    contentPadding = PaddingValues(16.dp)
                ) {
                    items(colors) { color ->
                        ColorItem(color = color)
                    }
                }
            }
        }
    }
}

@Composable
fun ColorItem(color: ColorEntity) {
    val parsedColor = Color(AndroidColor.parseColor(color.color))

    val dateFormat = SimpleDateFormat("dd MMM yyyy", Locale.getDefault())
    val dateString = dateFormat.format(Date(color.time))

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .background(
                color = parsedColor,
                shape = MaterialTheme.shapes.medium
            )
            .padding(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Top
        ) {

            val textWidth = 120.dp
            Text(
                modifier = Modifier
                    .width(textWidth - 10.dp),
                text = color.color,
                color = Color.White,
                fontSize = 18.sp
            )

            Spacer(modifier = Modifier.height(4.dp))

            Box(
                modifier = Modifier
                    .height(1.dp)
                    .width(textWidth - 10.dp)
                    .background(Color.White)
            )
        }

        Spacer(modifier = Modifier.height(120.dp))

        Text(
            text = "   Created at\n$dateString",
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(8.dp),
            style = MaterialTheme.typography.bodySmall,
            color = Color.White,
            fontSize = 14.sp
        )
    }
}