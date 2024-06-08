package com.example.projket_roboty_mobilne.ui.screens

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.example.projket_roboty_mobilne.R
import com.example.projket_roboty_mobilne.data.UserDataUIEvents
import com.example.projket_roboty_mobilne.ui.ButtonComponent
import com.example.projket_roboty_mobilne.ui.ConsoleArrows
import com.example.projket_roboty_mobilne.ui.RobotImage
import com.example.projket_roboty_mobilne.ui.TextComponent
import com.example.projket_roboty_mobilne.ui.TextFieldControllerComponent
import com.example.projket_roboty_mobilne.ui.TopBar
import com.example.projket_roboty_mobilne.ui.TwoButtonsComponent
import com.example.projket_roboty_mobilne.ui.UserInputViewModel

@SuppressLint("SuspiciousIndentation")
@Composable
fun DrivingRobotScreen(robotName: String?, description: String?, max_speed: Int?, navController: NavController) {
    val userInputViewModel: UserInputViewModel = viewModel()
    var buttonText by remember {
        mutableStateOf("Connect")
    }
    var connectionState by remember {
        mutableStateOf(false)
    }
    Surface (
        modifier = Modifier.fillMaxSize()
    ){
            Column {
                TopBar(ualue = "$robotName", navController)
                TextComponent(textValue = "Driving Robot", textSize = 20.sp)
                TextComponent(textValue = description.toString(), textSize = 16.sp)
                Spacer(modifier = Modifier.size(50.dp))
                ButtonComponent(text = buttonText, clickAction = {
                        if(buttonText == "Connect") {
                            connectionState = true
                            buttonText = "Disconnect"
                        }
                        else {
                            connectionState = false
                            buttonText = "Connect"
                        }
                })
                if(connectionState == true){
                TextComponent(textValue = "Max Robot Speed = ${max_speed}", textSize = 20.sp)
                TextFieldControllerComponent {
                    var selectedSpeed = it
                    if (max_speed != null) {
                        if(selectedSpeed > max_speed.toInt())
                            selectedSpeed = max_speed
                            userInputViewModel.onEvent(
                                UserDataUIEvents.SelectSpeedEvent(selectedSpeed)
                            )
                    }
                }
                TextComponent(textValue = "Current speed: ${userInputViewModel.uiControllerState.value.currentSpeed}", textSize = 20.sp)
                ConsoleArrows(userInputViewModel)
                }
                RobotImage(image = R.drawable.driver)

            }

    }


}

@Preview
@Composable
fun DrivingRobotScreenPreview(){
    DrivingRobotScreen("robotName", "description", 25, rememberNavController())
}