package com.example.projket_roboty_mobilne.ui

import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.example.projket_roboty_mobilne.DataBase.MobileRobot
import com.example.projket_roboty_mobilne.data.CreateMobileRobotState
import com.example.projket_roboty_mobilne.data.UserControllerState
import com.example.projket_roboty_mobilne.data.UserDataUIEvents
import com.example.projket_roboty_mobilne.data.UserInputScreenState

class UserInputViewModel : ViewModel() {

    companion object{
        const val TAG = "UserInputViewModel"
    }
    var uiState = mutableStateOf(UserInputScreenState())
    var uiControllerState = mutableStateOf(UserControllerState())
    var createMobileRobotState = mutableStateOf(CreateMobileRobotState())

    fun onEvent(event: UserDataUIEvents){
        when(event)
        {
            is UserDataUIEvents.ButtonClicked -> {
                uiState.value = uiState.value.copy(
                    variantSelected = event.value
                )
            }

            is UserDataUIEvents.VariantSelected ->{
                uiState.value = uiState.value.copy(
                    variantSelected = event.variant
                )
                Log.d(TAG, "onEvent:VariantSelected->> ")
                Log.d(TAG, "${uiState.value}")
            }

            is UserDataUIEvents.ObjectSelected ->{
                uiState.value = uiState.value.copy(
                    robot = event.selectedRobot
                )
                Log.d(TAG, "onEvent:ObjectSelected->> ")
                Log.d(TAG, uiState.value.robot.robotName)
                Log.d(TAG, uiState.value.robot.robotType)
                Log.d(TAG, "${uiState.value.robot.id}")
            }

            is UserDataUIEvents.Connect -> {
                uiState.value = uiState.value.copy(
                    ifConnected = event.connected
                )
            }

            is UserDataUIEvents.MeasureSpeed -> {
                uiState.value = uiState.value.copy(
                    currentSpeed = event.speed
                )
            }

            is UserDataUIEvents.SelectSpeedEvent ->{
                    uiControllerState.value = uiControllerState.value.copy(
                    selectedSpeed = event.selectedSpeed
                )
            }
            is UserDataUIEvents.MoveEvent -> {
                uiControllerState.value = uiControllerState.value.copy(
                    currentSpeed = event.Speed
                )

            }
            is UserDataUIEvents.StopEvent ->{
                uiControllerState.value = uiControllerState.value.copy(
                    currentSpeed = 0
                )
            }

            is UserDataUIEvents.FlyEvent -> {
                uiControllerState.value = uiControllerState.value.copy(
                    currentHeight = event.height
                )
            }

            is UserDataUIEvents.StopFlyEvent -> {
                uiControllerState.value = uiControllerState.value.copy(
                    currentHeight = 0
                )
            }

            is UserDataUIEvents.CreateMobileRobotEvent ->{
                createMobileRobotState.value = createMobileRobotState.value.copy(
                    robotName = event.robot.robotName,
                    robotType = event.robot.robotType,
                    description = event.robot.description,
                    maxSpeed = event.robot.maxSpeed
                )
            }
        }
    }
}



