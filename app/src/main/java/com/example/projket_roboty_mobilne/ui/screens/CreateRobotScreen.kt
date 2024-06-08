package com.example.projket_roboty_mobilne.ui.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.projket_roboty_mobilne.DataBase.DataBaseHandler
import com.example.projket_roboty_mobilne.DataBase.MobileRobot
import com.example.projket_roboty_mobilne.DataBase.RobotTypes
import com.example.projket_roboty_mobilne.ui.ButtonComponent
import com.example.projket_roboty_mobilne.ui.CheckboxComponent
import com.example.projket_roboty_mobilne.ui.TextComponent
import com.example.projket_roboty_mobilne.ui.TextFieldComponent
import com.example.projket_roboty_mobilne.ui.TopBar


@Composable
fun CreateRobotScreen(NaVController: NavHostController, context: Context = LocalContext.current,
                      db: DataBaseHandler = DataBaseHandler(context)
){

    var robotName by remember {
        mutableStateOf("")
    }
    var robotType by remember {
        mutableStateOf(RobotTypes.DRIVING_ROBOT)
    }
    var description by remember {
        mutableStateOf("")
    }
    var maxSpeed by remember {
        mutableStateOf(0)
    }

    Surface (
        modifier = Modifier.fillMaxSize()
    ){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)) {

            TopBar(ualue = "Create Mobile Robot", navController = NaVController)
            Spacer(modifier = Modifier.size(70.dp))
            TextComponent(textValue = "Enter Robot Name", textSize = 20.sp)
            TextFieldComponent {
                robotName = it
            }
            TextComponent(textValue = "Enter Robot Type", textSize = 20.sp)
            CheckboxComponent {

                if(it == -1)
                    robotType = "Error"
                else if (it == 0)
                    robotType = RobotTypes.DRIVING_ROBOT
                else if (it == 1)
                    robotType = RobotTypes.WALKING_ROBOT
                else if (it == 2)
                    robotType = RobotTypes.FLYING_ROBOT
            }
            TextComponent(textValue = "Enter Robot Description", textSize = 20.sp)
            TextFieldComponent {
                description = it
            }
            TextComponent(textValue = "Enter Robot Max Speed", textSize = 20.sp)
            TextFieldComponent {
                val isNumeric = it.toIntOrNull()
                if(isNumeric == null)
                    maxSpeed = 0
                else
                    maxSpeed = it.toInt()
            }

            ButtonComponent(text = "Add to Database") {
                if( maxSpeed == 0 || robotName == "" || robotType == "Error" || description == "")
                    Toast.makeText(context, "Enter all robot values", Toast.LENGTH_SHORT).show()
                else {
                    db.insertData(MobileRobot(robotType, robotName, maxSpeed, description))
                    NaVController.navigate(Routes.USER_INPUT_SCREEN)
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun CreateRobotScreenPreview(){
    //CreateRobotScreen()
}
