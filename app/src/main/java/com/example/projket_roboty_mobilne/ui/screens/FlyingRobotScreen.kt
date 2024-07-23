package com.example.projket_roboty_mobilne.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
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
import com.example.projket_roboty_mobilne.ui.FlyConsoleArrow
import com.example.projket_roboty_mobilne.ui.RobotImage
import com.example.projket_roboty_mobilne.ui.TextComponent
import com.example.projket_roboty_mobilne.ui.TextFieldControllerComponent
import com.example.projket_roboty_mobilne.ui.TopBar
import com.example.projket_roboty_mobilne.ui.TwoButtonsComponent
import com.example.projket_roboty_mobilne.ui.UserInputViewModel

@Composable
fun FlyingRobotScreen(robotName: String?, description: String?, max_speed: Int?, navController: NavController) {
    val userInputViewModel: UserInputViewModel = viewModel()
    var buttonText by remember {
        mutableStateOf("Connect")
    }
    var connectionState by remember {
        mutableStateOf(false)
    }
    var propellersState by remember {
        mutableStateOf(false)
    }
    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
            TopBar(ualue = "$robotName", navController)
            TextComponent(textValue = "Flying Robot", textSize = 20.sp)
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
            if(connectionState == true) {
                TwoButtonsComponent(
                    textFirstButton = "Propellers On",
                    textSecondButtton = "Propellers Off",
                    clickActionFirstButton = { propellersState = true},
                    clickActionSecondButton = {propellersState = false})
                if(propellersState){
                    TextComponent(textValue = "Max Robot Speed = ${max_speed}", textSize = 20.sp)
                    TextFieldControllerComponent {
                        userInputViewModel.onEvent(
                            UserDataUIEvents.SelectSpeedEvent(it)
                        )
                    }
                    TextComponent(
                        textValue = "Speed: ${userInputViewModel.uiControllerState.value.currentSpeed}",
                        textSize = 20.sp
                    )
                    TextComponent(
                        textValue = "Height: ${userInputViewModel.uiControllerState.value.currentHeight}",
                        textSize = 20.sp
                    )
                    Row {
                        Spacer(modifier = Modifier.size(20.dp))
                        FlyConsoleArrow(userInputViewModel)
                        Spacer(modifier = Modifier.size(70.dp))
                        ConsoleArrows(userInputViewModel, true)
                    }
                }}
            RobotImage(image = R.drawable.flyer)


        }

    }


}

@Preview
@Composable
fun FlyingRobotScreenPreview(){
    FlyingRobotScreen("robotName", "description", 25, rememberNavController())
}