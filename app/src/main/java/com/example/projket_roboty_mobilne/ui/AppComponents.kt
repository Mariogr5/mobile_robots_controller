package com.example.projket_roboty_mobilne.ui

import android.view.MotionEvent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInteropFilter
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.projket_roboty_mobilne.DataBase.DataBaseHandler
import com.example.projket_roboty_mobilne.DataBase.MobileRobot
import com.example.projket_roboty_mobilne.DataBase.RobotTypes
import com.example.projket_roboty_mobilne.R
import com.example.projket_roboty_mobilne.data.UserControllerState
import com.example.projket_roboty_mobilne.data.UserDataUIEvents
import com.example.projket_roboty_mobilne.ui.screens.Routes
import org.jetbrains.annotations.ApiStatus.Internal

@Composable
fun TopBar(ualue: String, navController: NavController)
{

    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = ualue,//"Witaj w menadżerze urządzeń mobilnych!",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )
        
        Spacer(modifier = Modifier.weight(1f))
        
        Image(
            modifier = Modifier
                .size(80.dp)
                .clickable(onClick = { navController.navigate(Routes.WELCOME_SCREEN) }),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo"

        )
    }
}

@Preview(showBackground = true)
@Composable
fun TopBarPreview(){
    TopBar("Witaj w menadżerze urządzeń mobilnych!", rememberNavController())
}


@Composable
fun TextComponent(textValue: String, textSize : TextUnit, colorValue: Color = Color.Black)
{
    Text(text = textValue,
        fontSize = textSize,
        color = colorValue,
        fontWeight = FontWeight.Light
    )
}
@Preview(showBackground = true)
@Composable
fun TextComponentPreview()
{
    TextComponent(textValue = "Example Text", textSize = 24.sp)
}

@Composable
fun StartButton(NavController: NavHostController)
{
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier.fillMaxSize()
    ) {
        Button(

            onClick = {  NavController.navigate(Routes.USER_INPUT_SCREEN) },
            modifier = Modifier
                .size(300.dp, 150.dp)
                .clip(RoundedCornerShape(16.dp)),
            shape = RoundedCornerShape(16.dp)

        ) {
        Text(text = "START")
        }
    }

}

@Preview(showBackground = true)
@Composable
fun StartButtonPreview()
{
    StartButton(rememberNavController())
}

@Composable
fun TextFieldComponent(
    onTextChange: (name:String) -> Unit
)
{
    var currentValue by remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = currentValue,
        onValueChange = {
            currentValue = it
            onTextChange(it)
        },
        placeholder = {
            Text(text = "Enter your value", fontSize = 18.sp)
        },
        textStyle = TextStyle.Default.copy(fontSize = 24.sp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        }
        )
}

@Preview(showBackground = true)
@Composable
fun TextFieldComponentPreview()
{
    //TextFieldComponent()
}

@Composable
fun TextFieldControllerComponent(
    MoveEvent: (speed:Int) -> Unit
)
{
    var currentValue by remember {
        mutableStateOf("")
    }
    val localFocusManager = LocalFocusManager.current
    OutlinedTextField(
        modifier = Modifier.fillMaxWidth(),
        value = currentValue,
        onValueChange = {
            currentValue = it
            MoveEvent(currentValue.toInt())
        },
        placeholder = {
            Text(text = "Enter robot speed", fontSize = 18.sp)
        },
        textStyle = TextStyle.Default.copy(fontSize = 24.sp),
        keyboardOptions = KeyboardOptions(
            imeAction = ImeAction.Done
        ),
        keyboardActions = KeyboardActions{
            localFocusManager.clearFocus()
        }
    )
}




@Composable
fun ButtonComponent(
    text: String,
    clickAction: () -> Unit
)
{
    Button(
        modifier = Modifier.fillMaxWidth(),
        onClick = { clickAction() })
    {
        TextComponent(textValue = text, textSize = 20.sp)


    }
}

@Preview(showBackground = true)
@Composable
fun ButtonComponentPreview()
{
    //ButtonComponent()
}

@Composable
fun TwoButtonsComponent(
    textFirstButton: String,
    textSecondButtton: String,
    clickActionFirstButton: () -> Unit,
    clickActionSecondButton: () -> Unit
){
    var VisibleState by remember {
        mutableStateOf(true)
    }
    Row(modifier = Modifier.fillMaxWidth()) {
        if(VisibleState) {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { clickActionFirstButton()
                VisibleState = false})
            {
                TextComponent(textValue = textFirstButton, textSize = 20.sp)
            }
        }
        else {
            Button(
                modifier = Modifier
                    .weight(1f)
                    .height(50.dp)
                    .padding(horizontal = 4.dp),
                shape = RoundedCornerShape(16.dp),
                onClick = { clickActionSecondButton()
                VisibleState = true})
            {
                TextComponent(textValue = textSecondButtton, textSize = 20.sp)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun TwoButtonsComponentPreview(){
    TwoButtonsComponent(
        textFirstButton = "Test1",
        textSecondButtton = "Test2",
        clickActionFirstButton = {  },
        clickActionSecondButton = {})
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownMenu(
    onTextChange: (variantSelected:String) -> Unit,
    listOfRobots: List<MobileRobot>
)
{
    var isExpanded by remember {
        mutableStateOf(false)
    }

    var variant by remember {
        mutableStateOf("")
    }

    var name by remember {
        mutableStateOf("")
    }

    Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = Alignment.Center
    )
    {
    ExposedDropdownMenuBox(expanded = isExpanded, onExpandedChange = {isExpanded = it}) {
        TextField(
            value = name,
            onValueChange = {},
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(expanded = isExpanded)
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor()
        )
        
        ExposedDropdownMenu(
            expanded = isExpanded,
            onDismissRequest = { isExpanded = false }

        ) {
            listOfRobots.forEachIndexed{index, robot ->
                DropdownMenuItem(
                text = {
                    Text(robot.robotName)
                       },
                onClick = {
                    isExpanded = false
                    name = robot.robotName
                    variant = robot.robotType
                    //onTextChange(variant)
                    onTextChange(name)
                })
            }
        }
    }
    }
}

@Preview(showBackground = true)
@Composable
fun DropDownMenuPreview()
{
    //DropDownMenu()
}



@Composable
fun RobotImage(image: Int)
{
    Surface(
        modifier = Modifier.fillMaxSize()
    ) {
        Image(
            modifier = Modifier.fillMaxSize(),
            painter = painterResource(id = image),
            contentDescription = "Driving Robot"
        )
    }

}


@Preview(showBackground = true)
@Composable
fun RobotImagePreview()
{
    RobotImage(image = R.drawable.driver)
}


@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ConsoleArrows(userInputViewModel: UserInputViewModel, ifFlying: Boolean = false) {
    var currentSpeed: Int = 0
    var isPressed_right by remember { mutableStateOf(false) }
    var isPressed_left by remember { mutableStateOf(false) }
    var isPressed_up by remember { mutableStateOf(false) }
    var isPressed_down by remember { mutableStateOf(false) }
    var modifier: Modifier = Modifier
    if(!ifFlying)
        modifier = Modifier.fillMaxWidth()

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        //modifier = Modifier.fillMaxWidth()
        modifier = modifier
    ) {

        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_up),
            contentDescription = "Up",
            modifier = Modifier
                .size(64.dp)
                .background(if (isPressed_up) Color.Gray else Color.LightGray)
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isPressed_up = true
                            currentSpeed = userInputViewModel.uiControllerState.value.selectedSpeed
                            userInputViewModel.onEvent(
                                UserDataUIEvents.MoveEvent(currentSpeed)
                            )
                            true
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            isPressed_up = false
                            userInputViewModel.onEvent(
                                UserDataUIEvents.StopEvent()
                            )
                            true
                        }

                        else -> false
                    }
                },
               )


        Spacer(modifier = Modifier.height(16.dp))
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_left),
                contentDescription = "Left",
                modifier = Modifier
                    .size(64.dp)
                    .background(if (isPressed_left) Color.Gray else Color.LightGray)
                    .pointerInteropFilter { motionEvent ->
                        when (motionEvent.action) {
                            MotionEvent.ACTION_DOWN -> {
                                isPressed_left = true
                                currentSpeed =
                                    userInputViewModel.uiControllerState.value.selectedSpeed
                                userInputViewModel.onEvent(
                                    UserDataUIEvents.MoveEvent(currentSpeed)
                                )
                                true
                            }

                            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                                isPressed_left = false
                                userInputViewModel.onEvent(
                                    UserDataUIEvents.StopEvent()
                                )
                                true
                            }

                            else -> false
                        }
                    },
            )
            Spacer(modifier = Modifier.width(24.dp))
            Icon(
                painter = painterResource(id = R.drawable.ic_arrow_right),
                contentDescription = "Right",
                modifier = Modifier
                    .size(64.dp)
                    .background(if (isPressed_right) Color.Gray else Color.LightGray)
                    //.fillMaxSize()
                    .pointerInteropFilter { motionEvent ->
                        when (motionEvent.action) {
                            MotionEvent.ACTION_DOWN -> {
                                isPressed_right = true
                                currentSpeed =
                                    userInputViewModel.uiControllerState.value.selectedSpeed
                                userInputViewModel.onEvent(
                                    UserDataUIEvents.MoveEvent(currentSpeed)
                                )
                                true
                            }

                            MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                                isPressed_right = false
                                userInputViewModel.onEvent(
                                    UserDataUIEvents.StopEvent()
                                )
                                true
                            }

                            else -> false
                        }
                    },
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        Icon(
            painter = painterResource(id = R.drawable.ic_arrow_down),
            contentDescription = "Down",
            modifier = Modifier
                .size(64.dp)
                .background(if (isPressed_down) Color.Gray else Color.LightGray)
                .pointerInteropFilter { motionEvent ->
                    when (motionEvent.action) {
                        MotionEvent.ACTION_DOWN -> {
                            isPressed_down = true
                            currentSpeed = userInputViewModel.uiControllerState.value.selectedSpeed
                            userInputViewModel.onEvent(
                                UserDataUIEvents.MoveEvent(currentSpeed)
                            )
                            true
                        }

                        MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                            isPressed_down = false
                            userInputViewModel.onEvent(
                                UserDataUIEvents.StopEvent()
                            )
                            true
                        }

                        else -> false
                    }
                },
        )
    }
}

@Preview(showBackground = true)
@Composable
fun ConsoleArrowsPreview()
{
    //ConsoleArrows()
}

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun FlyConsoleArrow(userInputViewModel: UserInputViewModel)
{
    var currentHeight by remember {
        mutableStateOf(0)
    }
    var isPressed_up by remember { mutableStateOf(false) }
    var isPressed_down by remember { mutableStateOf(false) }

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ) {


    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_up),
        contentDescription = "Up",
        modifier = Modifier
            .size(64.dp)
            .background(if (isPressed_up) Color.Gray else Color.LightGray)
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isPressed_up = true
                        currentHeight += 3
                        userInputViewModel.onEvent(
                            UserDataUIEvents.FlyEvent(currentHeight)
                        )
                        true
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        isPressed_up = false
                        userInputViewModel.onEvent(
                            UserDataUIEvents.FlyEvent(currentHeight)
                        )
                        true
                    }

                    else -> false
                }
            },
    )
    Spacer(modifier = Modifier.width(24.dp))
    Icon(
        painter = painterResource(id = R.drawable.ic_arrow_down),
        contentDescription = "Down",
        modifier = Modifier
            .size(64.dp)
            .background(if (isPressed_down) Color.Gray else Color.LightGray)
            .pointerInteropFilter { motionEvent ->
                when (motionEvent.action) {
                    MotionEvent.ACTION_DOWN -> {
                        isPressed_down = true
                        currentHeight -= 3
                        if (currentHeight < 0)
                            currentHeight = 0
                        userInputViewModel.onEvent(
                            UserDataUIEvents.FlyEvent(currentHeight)
                        )
                        true
                    }

                    MotionEvent.ACTION_UP, MotionEvent.ACTION_CANCEL -> {
                        isPressed_down = false
                        userInputViewModel.onEvent(
                            UserDataUIEvents.FlyEvent(currentHeight)
                        )
                        true
                    }

                    else -> false
                }
            },
    )
}
}

@Preview(showBackground = true)
@Composable
fun FlyConsoleArrowPreview()
{
    //FlyConsoleArrow()
}

@Composable
fun CheckboxComponent(boxSelected: (variantSelected:Int) -> Unit) {

    var selectedOption by remember {
        mutableStateOf(-1)
    }
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth()
    ) {
        Text(
            "Driving Robot"
        )
        Checkbox(
            checked = selectedOption == 0,
            onCheckedChange = {
                selectedOption = if (it) 0 else -1
            boxSelected(selectedOption)
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            "Walking Robot"
        )
        Checkbox(
            checked = selectedOption == 1,
            onCheckedChange = {
                selectedOption = if (it) 1 else -1
                boxSelected(selectedOption)
            }
        )
        Spacer(modifier = Modifier.size(30.dp))
        Text(
            "Flying Robot"
        )
        Checkbox(
            checked = selectedOption == 2,
            onCheckedChange = {
                selectedOption = if (it) 2 else -1
                boxSelected(selectedOption)
            }
        )
    }

}

@Preview
@Composable
fun CheckboxComponentPreview() {
    CheckboxComponent({})
}

@Composable
fun DeleteListItem(robot: MobileRobot, db: DataBaseHandler, NavController: NavHostController)
{
    Row (modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically){
        Text(
            text = robot.robotName,//"Witaj w menadżerze urządzeń mobilnych!",
            color = Color.Black,
            fontSize = 24.sp,
            fontWeight = FontWeight.Medium
        )

        Spacer(modifier = Modifier.weight(1f))

        Image(
            modifier = Modifier
                .size(80.dp)
                .clickable(onClick = {
                    db.deleteData(robot.id)
                    NavController.navigate(Routes.DELETE_ROBOT_SCREEN)
                }),
            painter = painterResource(id = R.drawable.trush),
            contentDescription = "Logo"

        )
    }
}
