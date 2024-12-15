package com.example.motivatordayapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun MoodTrackerScreen() {
    val moodEmojis = listOf(
        "😢" to "Очень плохо",
        "🙁" to "Плохо",
        "😐" to "Нормально",
        "🙂" to "Хорошо",
        "😄" to "Замечательно"
    )
    val moodEntries = remember { mutableStateListOf<Pair<String, String>>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Какое у Вас сейчас настроение?",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        moodEmojis.forEach { (emoji, mood) ->
            Button(
                onClick = {
                    val currentTime = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                    moodEntries.add("$emoji $mood" to currentTime)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 4.dp)
            ) {
                Text(emoji, fontSize = 24.sp)
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "История Ваших настроений:",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        moodEntries.forEach { (mood, timestamp) ->
            Text(
                text = "$timestamp: $mood",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}