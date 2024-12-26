package com.technopradyumn.randomcolor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.technopradyumn.randomcolor.ui.theme.RandomColorTheme
import dagger.hilt.android.AndroidEntryPoint
import com.technopradyumn.randomcolor.ui.ColorAppScreen

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ColorAppScreen()
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ColorAppScreenPreview() {
    RandomColorTheme {
        ColorAppScreen()
    }
}