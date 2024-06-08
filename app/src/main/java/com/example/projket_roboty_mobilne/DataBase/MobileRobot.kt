package com.example.projket_roboty_mobilne.DataBase

class MobileRobot {
    var id: Int = -1
    var robotType: String = ""
    var robotName: String = ""
    var maxSpeed: Int = 0
    var description: String = ""

    constructor(RobotType: String, RobotName: String, MaxSpeed: Int, Description: String)
    {
        this.robotType = RobotType
        this.robotName = RobotName
        this.maxSpeed = MaxSpeed
        this.description = Description
    }
    constructor()
    {}
}