package com.example.projket_roboty_mobilne.data

import com.example.projket_roboty_mobilne.DataBase.MobileRobot

sealed class UserDataUIEvents
{
    data class ButtonClicked(val value: String) : UserDataUIEvents()
    data class VariantSelected(val variant: String) : UserDataUIEvents()
    data class ObjectSelected(val selectedRobot: MobileRobot) : UserDataUIEvents()
    data class Connect(val connected: Boolean) : UserDataUIEvents()

    data class MeasureSpeed(val speed: Int) : UserDataUIEvents()

    data class SelectSpeedEvent(val selectedSpeed: Int) : UserDataUIEvents()
    data class MoveEvent(val Speed: Int): UserDataUIEvents()
    data class StopEvent(val stopSpeed: Int = 0): UserDataUIEvents()

    data class FlyEvent(val height: Int): UserDataUIEvents()
    data class StopFlyEvent(val down: Int = 0): UserDataUIEvents()


    data class CreateMobileRobotEvent(val robot: MobileRobot) : UserDataUIEvents()
}


