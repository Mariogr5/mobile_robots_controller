package com.example.projket_roboty_mobilne

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import com.example.projket_roboty_mobilne.ui.screens.MobileRobotsNavigationGraph
import com.example.projket_roboty_mobilne.ui.theme.Projket_roboty_mobilneTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Projket_roboty_mobilneTheme {

                Projekt_robotyMobilneApp()
            }
        }
    }
}


@Composable
fun Projekt_robotyMobilneApp()
{

    MobileRobotsNavigationGraph()
}