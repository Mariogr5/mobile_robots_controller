package com.example.projket_roboty_mobilne.data

import com.example.projket_roboty_mobilne.DataBase.RobotTypes

data class CreateMobileRobotState(
    var robotName: String = "",
    var robotType: String = RobotTypes.DRIVING_ROBOT,
    var description: String = "",
    var maxSpeed: Int = 0
)
