package com.example.projket_roboty_mobilne.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projket_roboty_mobilne.ui.StartButton
import com.example.projket_roboty_mobilne.ui.TextComponent
import com.example.projket_roboty_mobilne.ui.TopBar

@Composable
fun WelcomeScreen(NavController: NavHostController){

    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(18.dp))
        {
            TopBar("Welcome", NavController)
            TextComponent(textValue = "Welcome in Mobile Manager!!. Mobile Manager Application allows you to select your mobile robots and add to your account.", textSize = 24.sp)
            Spacer(modifier = Modifier.size(50.dp))
            TextComponent(textValue = "Click Start to beginn your application. Have fun !!!", textSize = 24.sp)
            Spacer(modifier = Modifier.size(10.dp))
            StartButton(NavController = NavController)

        }
//        Text(
//            modifier = Modifier
//                .fillMaxWidth()
//                .height(80.dp),
//            text = Routes.WELCOME_SCREEN
//        )


    }
}

@Preview
@Composable
fun WelcomeScreenPreview()
{
    WelcomeScreen(rememberNavController())
}