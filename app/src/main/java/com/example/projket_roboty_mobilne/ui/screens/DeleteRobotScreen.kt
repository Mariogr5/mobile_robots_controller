package com.example.projket_roboty_mobilne.ui.screens

import android.content.Context
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.projket_roboty_mobilne.DataBase.DataBaseHandler
import com.example.projket_roboty_mobilne.ui.DeleteListItem

@Composable
fun DeleteRobotScreen(NavController: NavHostController, context: Context = LocalContext.current,
                      db: DataBaseHandler = DataBaseHandler(context)
){
    var allrobots = db.readData()
    var items = remember {
        listOf(allrobots)
    }

//    LazyColumn {
//        items(items){ item ->
//                DeleteListItem(robot = item., db = db)
//        }
//    }

    items.forEachIndexed{index, mobileRobots ->
        DeleteListItem(robot = mobileRobots[index], db = db)
    }
}

@Preview
@Composable
fun DeleteRobotScreenPreview()
{
    //DeleteRobotScreenPreview()
}