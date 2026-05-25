package com.mariam.myapplication

import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.RadioButton
import androidx.compose.material3.RadioButtonDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.util.Calendar

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            StudentFormScreen()
        }
    }
}

@Composable
fun StudentFormScreen() {
    val context = LocalContext.current

    var nameState by remember { mutableStateOf("Mariam") }
    var surnameState by remember { mutableStateOf("") }
    var emailState by remember { mutableStateOf("") }
    var dateState by remember { mutableStateOf("") }

    var selectedOption by remember { mutableStateOf("Android") }
    var isAgreed by remember { mutableStateOf(true) }

    val directions = listOf("Android", "iOS", "Web")

    fun openDatePicker() {
        val calendar = Calendar.getInstance()

        DatePickerDialog(
            context,
            { _, year, month, dayOfMonth ->
                val day = dayOfMonth.toString().padStart(2, '0')
                val monthText = (month + 1).toString().padStart(2, '0')
                dateState = "$day/$monthText/$year"
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        ).show()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.radialGradient(
                    colors = listOf(
                        Color(0xFF243B55),
                        Color(0xFF141E30),
                        Color(0xFF050505)
                    )
                )
            )
            .padding(18.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center
        ) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(36.dp),
                colors = CardDefaults.cardColors(
                    containerColor = Color(0xFF101828)
                ),
                border = BorderStroke(
                    width = 2.dp,
                    brush = Brush.horizontalGradient(
                        colors = listOf(
                            Color(0xFF00F5D4),
                            Color(0xFFFF006E),
                            Color(0xFFFFBE0B)
                        )
                    )
                ),
                elevation = CardDefaults.cardElevation(defaultElevation = 14.dp)
            ) {
                Column(
                    modifier = Modifier.padding(24.dp),
                    verticalArrangement = Arrangement.spacedBy(15.dp)
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Box(
                            modifier = Modifier
                                .clip(CircleShape)
                                .background(
                                    Brush.linearGradient(
                                        colors = listOf(
                                            Color(0xFFFF006E),
                                            Color(0xFFFFBE0B)
                                        )
                                    )
                                )
                                .padding(horizontal = 18.dp, vertical = 13.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(
                                text = "M",
                                fontSize = 28.sp,
                                fontWeight = FontWeight.Black,
                                color = Color.White
                            )
                        }

                        Spacer(modifier = Modifier.width(14.dp))

                        Column {
                            Text(
                                text = "Neon Student Form",
                                fontSize = 24.sp,
                                fontWeight = FontWeight.ExtraBold,
                                fontFamily = FontFamily.Serif,
                                color = Color.White
                            )

                            Text(
                                text = "შეავსე მონაცემები და გაგზავნე ფორმა",
                                fontSize = 13.sp,
                                color = Color(0xFFCBD5E1)
                            )
                        }
                    }

                    Spacer(modifier = Modifier.height(4.dp))

                    NeonInputField(
                        value = nameState,
                        onValueChange = { nameState = it },
                        label = "სახელი"
                    )

                    NeonInputField(
                        value = surnameState,
                        onValueChange = { surnameState = it },
                        label = "გვარი"
                    )

                    NeonInputField(
                        value = emailState,
                        onValueChange = { emailState = it },
                        label = "იმეილი",
                        keyboardType = KeyboardType.Email
                    )

                    Button(
                        onClick = { openDatePicker() },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(56.dp),
                        shape = RoundedCornerShape(20.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF00F5D4)
                        )
                    ) {
                        Text(
                            text = if (dateState.isEmpty()) {
                                "აირჩიე თარიღი"
                            } else {
                                "თარიღი: $dateState"
                            },
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color(0xFF101828)
                        )
                    }

                    Text(
                        text = "ფავორიტი მიმართულება",
                        fontSize = 17.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )

                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFF1D2939))
                            .padding(12.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        directions.forEach { direction ->
                            DirectionOption(
                                title = direction,
                                selected = selectedOption == direction,
                                onClick = { selectedOption = direction }
                            )
                        }
                    }

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clip(RoundedCornerShape(24.dp))
                            .background(Color(0xFF111827))
                            .border(
                                width = 1.dp,
                                color = Color(0xFFFFBE0B),
                                shape = RoundedCornerShape(24.dp)
                            )
                            .padding(14.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Switch(
                            checked = isAgreed,
                            onCheckedChange = { isAgreed = it },
                            colors = SwitchDefaults.colors(
                                checkedThumbColor = Color.White,
                                checkedTrackColor = Color(0xFFFF006E),
                                uncheckedThumbColor = Color.White,
                                uncheckedTrackColor = Color(0xFF475569)
                            )
                        )

                        Spacer(modifier = Modifier.width(12.dp))

                        Text(
                            text = "ვეთანხმები წესებს და პირობებს",
                            fontSize = 14.sp,
                            fontWeight = FontWeight.Medium,
                            color = Color.White
                        )
                    }

                    Button(
                        onClick = {
                            if (
                                nameState.isBlank() ||
                                surnameState.isBlank() ||
                                emailState.isBlank() ||
                                dateState.isBlank() ||
                                selectedOption.isBlank() ||
                                !isAgreed
                            ) {
                                Toast.makeText(
                                    context,
                                    "შეავსეთ ყველა ველი!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            } else {
                                Toast.makeText(
                                    context,
                                    "მონაცემები გაიგზავნა!",
                                    Toast.LENGTH_SHORT
                                ).show()
                            }
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(60.dp),
                        shape = RoundedCornerShape(24.dp),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFFFF006E)
                        )
                    ) {
                        Text(
                            text = "Submit",
                            fontSize = 19.sp,
                            fontWeight = FontWeight.Black,
                            color = Color.White
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun NeonInputField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        label = {
            Text(text = label)
        },
        modifier = Modifier.fillMaxWidth(),
        singleLine = true,
        shape = RoundedCornerShape(20.dp),
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.White,
            unfocusedTextColor = Color.White,
            focusedContainerColor = Color(0xFF1D2939),
            unfocusedContainerColor = Color(0xFF1D2939),
            focusedBorderColor = Color(0xFF00F5D4),
            unfocusedBorderColor = Color(0xFF475569),
            focusedLabelColor = Color(0xFF00F5D4),
            unfocusedLabelColor = Color(0xFFCBD5E1),
            cursorColor = Color(0xFF00F5D4)
        )
    )
}

@Composable
fun DirectionOption(
    title: String,
    selected: Boolean,
    onClick: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(18.dp))
            .background(
                if (selected) Color(0xFF312E81) else Color(0xFF0F172A)
            )
            .border(
                width = 1.dp,
                color = if (selected) Color(0xFF00F5D4) else Color(0xFF334155),
                shape = RoundedCornerShape(18.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 10.dp, vertical = 6.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        RadioButton(
            selected = selected,
            onClick = onClick,
            colors = RadioButtonDefaults.colors(
                selectedColor = Color(0xFF00F5D4),
                unselectedColor = Color(0xFF94A3B8)
            )
        )

        Text(
            text = title,
            fontSize = 16.sp,
            fontWeight = FontWeight.Bold,
            color = if (selected) Color.White else Color(0xFFCBD5E1)
        )
    }
}