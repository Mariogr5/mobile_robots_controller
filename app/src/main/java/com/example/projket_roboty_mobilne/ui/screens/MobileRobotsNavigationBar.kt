package com.example.projket_roboty_mobilne.ui.screens

import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.projket_roboty_mobilne.DataBase.RobotTypes
import com.example.projket_roboty_mobilne.data.UserInputScreenState
import com.example.projket_roboty_mobilne.ui.UserInputViewModel

@Composable
fun MobileRobotsNavigationGraph(userInputViewModel: UserInputViewModel = viewModel())
{
    val NavController = rememberNavController()

    NavHost(navController = NavController, startDestination = Routes.WELCOME_SCREEN){
        var uiState = mutableStateOf(UserInputScreenState())

        composable(Routes.USER_INPUT_SCREEN)
        {
            UserInputScreen(userInputViewModel, NavController, showControlPanelScreen =
            {
                println()
                if(it.robotType == RobotTypes.DRIVING_ROBOT)
                    NavController.navigate(Routes.DRIVING_ROBOT_SCREEN+"/${it.robotName}/${it.description}/${it.maxSpeed}")
                else if(it.robotType == RobotTypes.WALKING_ROBOT)
                    NavController.navigate(Routes.WALKING_ROBOT_SCREEN+"/${it.robotName}/${it.description}/${it.maxSpeed}")
                else
                    NavController.navigate(Routes.FLYING_ROBOT_SCREEN+"/${it.robotName}/${it.description}/${it.maxSpeed}")
            }
            )
        }

        composable(Routes.WELCOME_SCREEN)
        {
            WelcomeScreen(NavController)
        }

        composable("${Routes.DRIVING_ROBOT_SCREEN}/{${Routes.ROBOT_NAME}}/{${Routes.ROBOT_DESCRIPTION}}/{${Routes.ROBOT_MAX_SPEED}}",
            arguments = listOf(
                navArgument(name = Routes.ROBOT_NAME){type = NavType.StringType},
                navArgument(name = Routes.ROBOT_DESCRIPTION){type = NavType.StringType},
                navArgument(name = Routes.ROBOT_MAX_SPEED){type = NavType.IntType},
                )
            )
        {
            val robotName = it?.arguments?.getString(Routes.ROBOT_NAME)
            val description = it?.arguments?.getString(Routes.ROBOT_DESCRIPTION)
            val max_speed = it?.arguments?.getInt(Routes.ROBOT_MAX_SPEED)
            DrivingRobotScreen(robotName, description, max_speed, NavController)
        }

        composable("${Routes.WALKING_ROBOT_SCREEN}/{${Routes.ROBOT_NAME}}/{${Routes.ROBOT_DESCRIPTION}}/{${Routes.ROBOT_MAX_SPEED}}",

            arguments = listOf(
                navArgument(name = Routes.ROBOT_NAME){type = NavType.StringType},
                navArgument(name = Routes.ROBOT_DESCRIPTION){type = NavType.StringType},
                navArgument(name = Routes.ROBOT_MAX_SPEED){type = NavType.IntType},
            ))
        {
            val robotName = it?.arguments?.getString(Routes.ROBOT_NAME)
            val description = it?.arguments?.getString(Routes.ROBOT_DESCRIPTION)
            val max_speed = it?.arguments?.getInt(Routes.ROBOT_MAX_SPEED)
            WalkingRobotScreen(robotName, description, max_speed, NavController)
        }
        composable("${Routes.FLYING_ROBOT_SCREEN}/{${Routes.ROBOT_NAME}}/{${Routes.ROBOT_DESCRIPTION}}/{${Routes.ROBOT_MAX_SPEED}}",
            arguments = listOf(
                navArgument(name = Routes.ROBOT_NAME){type = NavType.StringType},
                navArgument(name = Routes.ROBOT_DESCRIPTION){type = NavType.StringType},
                navArgument(name = Routes.ROBOT_MAX_SPEED){type = NavType.IntType},
            ))
        {
            val robotName = it?.arguments?.getString(Routes.ROBOT_NAME)
            val description = it?.arguments?.getString(Routes.ROBOT_DESCRIPTION)
            val max_speed = it?.arguments?.getInt(Routes.ROBOT_MAX_SPEED)
            FlyingRobotScreen(robotName, description, max_speed, NavController)

        }

        composable(Routes.CREATE_ROBOT_SCREEN){
            CreateRobotScreen(NavController)
        }

        composable(Routes.DELETE_ROBOT_SCREEN){
            DeleteRobotScreen(NavController)
        }
    }
}