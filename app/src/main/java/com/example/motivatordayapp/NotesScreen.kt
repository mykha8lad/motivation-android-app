package com.example.motivatordayapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@SuppressLint("NewApi")
@Composable
fun NotesScreen() {
    var noteText by remember { mutableStateOf("") }
    val notesList = remember { mutableStateListOf<Pair<String, String>>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = "Ваши заметки",
            fontSize = 24.sp,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        TextField(
            value = noteText,
            onValueChange = { noteText = it },
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            textStyle = LocalTextStyle.current.copy(fontSize = 16.sp),
            placeholder = { Text("Введите заметку...") }
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                if (noteText.isNotBlank()) {
                    val timestamp = LocalDateTime.now()
                        .format(DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm"))
                    notesList.add(noteText to timestamp)
                    noteText = ""
                }
            },
            modifier = Modifier.align(Alignment.End)
        ) {
            Text("Добавить заметку")
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Список заметок:",
            fontSize = 20.sp,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        notesList.forEach { (note, timestamp) ->
            Text(
                text = "$timestamp: $note",
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 4.dp)
            )
        }
    }
}