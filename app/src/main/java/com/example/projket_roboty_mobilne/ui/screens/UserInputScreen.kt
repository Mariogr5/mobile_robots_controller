package com.example.projket_roboty_mobilne.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.clickable
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projket_roboty_mobilne.DataBase.DataBaseHandler
import com.example.projket_roboty_mobilne.DataBase.MobileRobot
import com.example.projket_roboty_mobilne.DataBase.RobotTypes
import com.example.projket_roboty_mobilne.data.UserDataUIEvents
import com.example.projket_roboty_mobilne.ui.ButtonComponent
import com.example.projket_roboty_mobilne.ui.DropDownMenu
import com.example.projket_roboty_mobilne.ui.StartButton
import com.example.projket_roboty_mobilne.ui.TextComponent
import com.example.projket_roboty_mobilne.ui.TextFieldComponent
import com.example.projket_roboty_mobilne.ui.TopBar
import com.example.projket_roboty_mobilne.ui.UserInputViewModel

@Composable
fun UserInputScreen(userInputViewModel: UserInputViewModel,
                    NavController: NavHostController,
                    showControlPanelScreen: (robot: MobileRobot) -> Unit,
                    context: Context = LocalContext.current,
                    db: DataBaseHandler = DataBaseHandler(context)
) {
//    db.insertData(MobileRobot(RobotTypes.DRIVING_ROBOT,"Driver", 50, "Dupson"))
//    db.insertData(MobileRobot(RobotTypes.WALKING_ROBOT,"Walker", 50, "Dupson"))
//    db.insertData(MobileRobot(RobotTypes.FLYING_ROBOT,"Flyer", 50, "Dupson"))
    //var dupa = db.readDataByType(RobotTypes.DRIVING_ROBOT)
    val allrobots = db.readData()
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(18.dp))
        {
            TopBar(ualue = "Robot variant", NavController)
            TextComponent(textValue = "Enter your mobile robot variant", textSize = 16.sp)
            Spacer(modifier = Modifier.size(50.dp))
            DropDownMenu(
                onTextChange = {
                    userInputViewModel.onEvent(
                        UserDataUIEvents.VariantSelected(it)
                    )
                    var id = -1
                    for(robot in allrobots)
                    {
                        if(robot.robotType == it)
                        {
                            id = robot.id
                            break
                        }
                    }
                    var robot : MobileRobot
                    if(id != -1)
                        robot = db.readDataById(id)
                    else
                        robot = MobileRobot()
                    Toast.makeText(context,"Wysszukano",Toast.LENGTH_SHORT).show()
                    if(robot.id != -1) {
                        userInputViewModel.onEvent(
                            UserDataUIEvents.ObjectSelected(robot)
                        )
                    }
                    else {
                        robot = MobileRobot(RobotTypes.FLYING_ROBOT,"Zły robot", 50, "zlooo")
                        userInputViewModel.onEvent(
                            UserDataUIEvents.ObjectSelected(robot)
                        )
                    }
                },
                allrobots)
            Spacer(modifier = Modifier.size(50.dp))
            ButtonComponent (
                "Go to Control Panel",
                clickAction = {

                    //var robot: MobileRobot
                    val robot = userInputViewModel.uiState.value.robot
                    //robot = MobileRobot(RobotTypes.DRIVING_ROBOT, "My First Robot", 50, "My Roooobooot")
                    //NavController.navigate(Routes.DRIVING_ROBOT_SCREEN)
                    showControlPanelScreen(robot)
                })
            Spacer(modifier = Modifier.size(50.dp))
            TextComponent(textValue = "Create New Mobile Robot", textSize = 20.sp)
            Spacer(modifier = Modifier.size(50.dp))
            ButtonComponent (
                "Create new mobile robot profile",
                clickAction = {
                        NavController.navigate(Routes.CREATE_ROBOT_SCREEN)
                }
            )
            Spacer(modifier = Modifier.size(50.dp))
            TextComponent(textValue = "Delete mobile robot profile", textSize = 20.sp)
            Spacer(modifier = Modifier.size(50.dp))
            ButtonComponent (
                "Delete mobile robot profile",
                clickAction = {
                    NavController.navigate(Routes.DELETE_ROBOT_SCREEN)
                }
            )
        }
//        ButtonComponent (
//            gotToControlPanelScreen = {
//
//            }
//        )
//        TextFieldComponent( onTextChange = {
//            userInputViewModel.onEvent(
//                UserDataUIEvents.VariantSelected(it)
//            )
//        })


    }
}

@Preview
@Composable
fun UserInputScreenPreview()
{
    UserInputScreen(userInputViewModel = viewModel(),rememberNavController(), {})
}