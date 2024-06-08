package com.example.projket_roboty_mobilne.ui.screens

import android.content.Context
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.projket_roboty_mobilne.DataBase.DataBaseHandler
import com.example.projket_roboty_mobilne.ui.DeleteListItem
import com.example.projket_roboty_mobilne.ui.TopBar

@Composable
fun DeleteRobotScreen(NavController: NavHostController, context: Context = LocalContext.current,
                      db: DataBaseHandler = DataBaseHandler(context)
){
    //var allrobots = db.readData()
    var allrobots = db.readData()

//    LazyColumn {
//        items(allrobots){ items = allrobots, itemcontent{
//                DeleteListItem(robot = item., db = db)
//        }
//    }
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(18.dp)
            .verticalScroll(rememberScrollState())
        )
        {
            TopBar(ualue = "Delete Robot", navController = NavController)
            Spacer(modifier = Modifier.size(50.dp))
        allrobots.forEachIndexed { index, mobileRobot ->
            DeleteListItem(robot = mobileRobot, db = db, NavController)
            }
        }

//    items.forEachIndexed{index, mobileRobots ->
//        DeleteListItem(robot = mobileRobots[index], db = db)
//    }
    }
}

@Preview
@Composable
fun DeleteRobotScreenPreview()
{
    //DeleteRobotScreenPreview()
}