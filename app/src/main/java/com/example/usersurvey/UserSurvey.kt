package com.example.usersurvey

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import androidx.compose.material3.Text

@Composable
fun UserSurvey() {
    // Состояние для хранения данных
    val name = remember { mutableStateOf("") }
    val age = remember { mutableStateOf(25) }
    val isSubscribed = remember { mutableStateOf(false) }
    val selectedGender = remember { mutableStateOf("Male") }

    // Строки для отображения текста
    val nameError = if (name.value.isEmpty()) "Имя не может быть пустым" else ""

    val context = LocalContext.current

    // Логика для отображения тоста при нажатии на кнопку "Отправить"
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Аватар
        Image(
            painter = painterResource(id = R.drawable.avatar),
            contentDescription = "Аватар",
            modifier = Modifier.size(100.dp)
        )

        // Поле ввода имени
        TextField(
            value = name.value,
            onValueChange = { name.value = it },
            label = { Text("Введите имя") },
            modifier = Modifier.fillMaxWidth()
        )
        if (nameError.isNotEmpty()) {
            Text(
                text = nameError,
                color = Color.Red,
                style = MaterialTheme.typography.bodySmall
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Ползунок для выбора возраста
        Text("Возраст: ${age.value}")
        Slider(
            value = age.value.toFloat(),
            onValueChange = { age.value = it.toInt() },
            valueRange = 1f..100f,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Выбор пола
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ) {
            RadioButton(
                selected = selectedGender.value == "Male",
                onClick = { selectedGender.value = "Male" }
            )
            Text("Мужской")

            Spacer(modifier = Modifier.width(16.dp))

            RadioButton(
                selected = selectedGender.value == "Female",
                onClick = { selectedGender.value = "Female" }
            )
            Text("Женский")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Подписка на рассылку
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Checkbox(
                checked = isSubscribed.value,
                onCheckedChange = { isSubscribed.value = it }
            )
            Text("Хочу получать новости")
        }

        Spacer(modifier = Modifier.height(16.dp))

        // Кнопка "Отправить"
        Button(
            onClick = {
                if (name.value.isNotEmpty()) {
                    // Показываем тост с введенными данными
                    Toast.makeText(
                        context,
                        "Имя: ${name.value}\nВозраст: ${age.value}\nПол: ${selectedGender.value}\nПодписка: ${if (isSubscribed.value) "да" else "нет"}",
                        Toast.LENGTH_LONG
                    ).show()
                } else {
                    // Если имя пустое, показываем ошибку
                    Toast.makeText(context, "Имя не может быть пустым", Toast.LENGTH_SHORT).show()
                }
            },
            enabled = name.value.isNotEmpty(), // Кнопка будет доступна только если имя не пустое
            modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
            Text("Отправить")
        }
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewUserSurvey() {
    UserSurvey()
}
