package com.example.projket_roboty_mobilne.data

import com.example.projket_roboty_mobilne.DataBase.MobileRobot

data class UserInputScreenState(
    var variantSelected : String = "",
    var robot : MobileRobot = MobileRobot(),
    var ifConnected : Boolean = false,
    var currentSpeed: Int = 0
)
