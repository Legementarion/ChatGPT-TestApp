package com.example.chatgpttestapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.semantics.Role.Companion.Button
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.chatgpttestapp.ui.theme.ChatGPTTestAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ChatGPTTestAppTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(16.dp),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        var isValid by remember { mutableStateOf(false) }
                        ValidatingTextField(
                            isValid = { text -> isValidString(text, 10) },
                            onValueChange = { text ->
                                isValid = isValidString(text, 10)
                            }
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Button(
                            onClick = {
                                // Действия при нажатии кнопки
                                if (isValid) {
                                    // Текст валиден, выполните дополнительные действия
                                } else {
                                    // Текст не валиден, выполните соответствующие действия
                                }
                            }
                        ) {
                            Text("Submit")
                        }
                    }
                }

                // Sample inputs and edge cases
                val tests = listOf(
                    "Aa1!",          // valid
                    "Aa1!  ",        // invalid (spaces)
                    "Aa!",           // invalid (no digit)
                    "aa1!",          // invalid (no uppercase)
                    "AA1!",          // invalid (no lowercase)
                    "Aa1",           // invalid (no special char)
                    "Affffffffffffffffffffffff" + "a1!",  // invalid (over max length)
                    "Aa1!@#",        // valid
                    "A\na1!",        // invalid (newline)
                    "Aa\t1!"         // invalid (tab)
                )

                tests.forEach {
                    println("Input '$it' is ${if (isValidString(it, 10)) "valid" else "invalid"}")
                }
            }
        }
    }
}

@Composable
fun Greeting(modifier: Modifier = Modifier) {
    Text(
        text = "Hello Enter your text here!",
        modifier = modifier
    )
}

@Composable
fun ValidatingTextField(
    isValid: (String) -> Boolean,
    onValueChange: (String) -> Unit
) {
    var text by remember { mutableStateOf(TextFieldValue()) }
    val textColor = if (isValid(text.text)) Color.Black else Color.Red

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        BasicTextField(
            value = text,
            onValueChange = {
                text = it
                onValueChange(it.text)
            },
            textStyle = TextStyle(fontSize = 18.sp, color = textColor),
            singleLine = true,
            keyboardOptions = KeyboardOptions.Default.copy(
                imeAction = ImeAction.Done
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    // Дополнительные действия при нажатии "Готово" на клавиатуре
                }
            )
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            text = if (isValid(text.text)) "Valid" else "Not Valid",
            color = textColor,
            fontSize = 14.sp,
            textAlign = TextAlign.End,
            modifier = Modifier.fillMaxWidth()
        )
    }
}

@Preview
@Composable
fun ValidatingTextFieldPreview() {
    ValidatingTextField(
        isValid = { it.isNotEmpty() },
        onValueChange = {}
    )
}