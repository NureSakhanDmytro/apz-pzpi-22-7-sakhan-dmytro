package com.example.earth_program

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.earth_program.ui.theme.Earth_ProgramTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Earth_ProgramTheme {
                MainMenuApp()
            }
        }
    }
}

@Composable
fun MainMenuApp() {
    val items = listOf(
        "Аналіз ґрунту" to Icons.Default.Home,
        "Метео" to Icons.Default.List,
        "Сезони" to Icons.Default.Person,
        "Рекомендації" to Icons.Default.Info,
        "Адмін" to Icons.Default.Settings
    )

    var selectedIndex by remember { mutableStateOf(0) }

    Scaffold(
        bottomBar = {
            NavigationBar {
                items.forEachIndexed { index, (label, icon) ->
                    NavigationBarItem(
                        selected = selectedIndex == index,
                        onClick = { selectedIndex = index },
                        icon = { Icon(icon, contentDescription = label) },
                        label = { Text(label) }
                    )
                }
            }
        }
    ) { innerPadding ->
        Box(modifier = Modifier
            .padding(innerPadding)
            .fillMaxSize()
            .padding(16.dp)) {

            when (selectedIndex) {
                0 -> SoilAnalysisScreen()
                1 -> WeatherDataScreen()
                2 -> PlantingSeasonsScreen()
                3 -> RecommendationScreen()
                4 -> AdminScreen()
            }
        }
    }
}

@Composable
fun SoilAnalysisScreen() {
    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            "Ласкаво просимо до програми!",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))
        var location by remember { mutableStateOf("") }
        var ph by remember { mutableStateOf("") }
        var nitrogen by remember { mutableStateOf("") }
        var potassium by remember { mutableStateOf("") }
        var phosphorus by remember { mutableStateOf("") }
        var moisture by remember { mutableStateOf("") }
        var density by remember { mutableStateOf("") }
        var crop by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            OutlinedTextField(value = location, onValueChange = { location = it }, label = { Text("Локація") })
            OutlinedTextField(value = ph, onValueChange = { ph = it }, label = { Text("pH") })
            OutlinedTextField(value = nitrogen, onValueChange = { nitrogen = it }, label = { Text("Нітроген") })
            OutlinedTextField(value = potassium, onValueChange = { potassium = it }, label = { Text("Калій") })
            OutlinedTextField(value = phosphorus, onValueChange = { phosphorus = it }, label = { Text("Фосфор") })
            OutlinedTextField(value = moisture, onValueChange = { moisture = it }, label = { Text("Вологість") })
            OutlinedTextField(value = density, onValueChange = { density = it }, label = { Text("Щільність") })
            OutlinedTextField(value = crop, onValueChange = { crop = it }, label = { Text("Культура") })

            Button(onClick = {
                result = "Збережено: $location, pH=$ph, N=$nitrogen, P=$phosphorus, K=$potassium"
            }) {
                Text("Зберегти")
            }

            if (result.isNotEmpty()) {
                Text(result)
            }
        }
    }
}

@Composable
fun WeatherDataScreen() {
    var temperature by remember { mutableStateOf("") }
    var humidity by remember { mutableStateOf("") }
    var precipitation by remember { mutableStateOf("") }
    var windSpeed by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Метеорологічні дані", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(value = temperature, onValueChange = { temperature = it }, label = { Text("Температура") })
        OutlinedTextField(value = humidity, onValueChange = { humidity = it }, label = { Text("Вологість") })
        OutlinedTextField(value = precipitation, onValueChange = { precipitation = it }, label = { Text("Опади") })
        OutlinedTextField(value = windSpeed, onValueChange = { windSpeed = it }, label = { Text("Швидкість вітру") })
        OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Примітки") })

        Button(onClick = {
            result = "Збережено: T=$temperature°C, H=$humidity%, W=$windSpeed м/с"
        }) {
            Text("Зберегти")
        }

        if (result.isNotEmpty()) {
            Text(result)
        }
    }
}

@Composable
fun PlantingSeasonsScreen() {
    var crop by remember { mutableStateOf("") }
    var season by remember { mutableStateOf("") }
    var notes by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Сезони висадки", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(value = crop, onValueChange = { crop = it }, label = { Text("Культура") })
        OutlinedTextField(value = season, onValueChange = { season = it }, label = { Text("Сезон") })
        OutlinedTextField(value = notes, onValueChange = { notes = it }, label = { Text("Примітки") })

        Button(onClick = {
            result = "Збережено: $crop — $season"
        }) {
            Text("Зберегти")
        }

        if (result.isNotEmpty()) {
            Text(result)
        }
    }
}

@Composable
fun RecommendationScreen() {
    Column(modifier = Modifier.verticalScroll(rememberScrollState())) {
        Text(
            "Рекомендації по ґрунту",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        OutlinedTextField(
            value = "Рекомендации для анализа почвы в локации Харків:\n" +
                    "pH почвы в норме.\n" +
                    "Рекомендуется добавить азотные удобрения.\n" +
                    "Рекомендуется добавить калийные удобрения.\n" +
                    "Уровень фосфора в почве в норме.\n" +
                    "Влажность почвы в норме.\n" +
                    "Предупреждение: Плотность почвы слишком высокая! Рекомендуется обработка почвы для улучшения аэрации.\n" +
                    "Рекомендации для метеоусловий:\n" +
                    "Предупреждение: Температура слишком высокая! Рекомендуется защита от солнечных ожогов.\n" +
                    "Влажность воздуха в норме.\n" +
                    "Осадки в норме.\n" +
                    "Скорость ветра в норме.\n" +
                    "Рекомендации для метеоусловий:\n" +
                    "Температура в норме.\n" +
                    "Влажность воздуха в норме.\n" +
                    "Осадки в норме.\n" +
                    "Предупреждение: Высокая скорость ветра! Рекомендуется защита растений от ветра.\n" +
                    "Рекомендации для сезона Весна и культуры Кукурудза:\n" +
                    "Рекомендуется провести подкормку удобрениями.\n" +
                    "Для культуры Кукурудза не требуются дополнительные рекомендации.\n" +
                    "Рекомендации для сезона Літо и культуры Помідор:\n" +
                    "Сезон в норме, не требуется дополнительных действий.\n" +
                    "Для культуры Помідор не требуются дополнительные рекомендации..",
            onValueChange = {},
            label = { Text("Текст рекомендації") },
            modifier = Modifier.fillMaxHeight(),
            readOnly = true
        )
    }
}

@Composable
fun AdminScreen() {
    var username by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var role by remember { mutableStateOf("") }
    var result by remember { mutableStateOf("") }

    Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
        Text("Адміністрування", style = MaterialTheme.typography.titleMedium)
        OutlinedTextField(value = username, onValueChange = { username = it }, label = { Text("Логін") })
        OutlinedTextField(value = password, onValueChange = { password = it }, label = { Text("Пароль") })
        OutlinedTextField(value = role, onValueChange = { role = it }, label = { Text("Роль") })

        Button(onClick = {
            result = "Збережено: $username ($role)"
        }) {
            Text("Зберегти")
        }

        if (result.isNotEmpty()) {
            Text(result)
        }
    }
}
