package com.example.motivatordayapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun MotivatorApp() {
    val today = LocalDateTime.now()
    val formattedDate = today.format(DateTimeFormatter.ofPattern("dd MMMM yyyy"))
    val motivationalTexts = listOf(
        "Сделай сегодня то, что другие не хотят, завтра будешь жить так, как другие не могут.",
        "Успех — это сумма мелких усилий, повторяемых день за днем.",
        "Вернись к цели, если вдруг устал.",
        "Каждый великий успех начинается с решения попробовать.",
        "Ты можешь больше, чем думаешь!"
    )

    // Выбор мотивационной фразы в зависимости от дня
    val dayOfYear = today.dayOfYear
    val motivationalText = motivationalTexts[dayOfYear % motivationalTexts.size]

    var selectedTab by remember { mutableStateOf(0) }

    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TabRow(selectedTabIndex = selectedTab) {
                Tab(
                    selected = selectedTab == 0,
                    onClick = { selectedTab = 0 },
                    text = { Text("Главная") }
                )
                Tab(
                    selected = selectedTab == 1,
                    onClick = { selectedTab = 1 },
                    text = { Text("Заметки") }
                )
                Tab(
                    selected = selectedTab == 2,
                    onClick = { selectedTab = 2 },
                    text = { Text("Настроение") }
                )
            }
            when (selectedTab) {
                0 -> MotivationScreen(formattedDate, motivationalText)
                1 -> NotesScreen()
                2 -> MoodTrackerScreen()
            }
        }
    }
}
