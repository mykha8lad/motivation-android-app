package com.example.motivatordayapp

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.Modifier
import java.time.LocalDateTime

@SuppressLint("NewApi")
@Composable
fun MotivationScreen(date: String, motivationalText: String) {
    val philosopherQuotes = listOf(
        "Будь изменением, которое ты хочешь видеть в мире. — Махатма Ганди",
        "Счастье зависит от нас самих. — Аристотель",
        "Войны выигрываются не числом, а умением. — Александр Македонский",
        "Знание — сила. — Фрэнсис Бэкон",
        "Чем больше мы знаем, тем больше растём. — Сократ"
    )
    val today = LocalDateTime.now()
    val dayOfYear = today.dayOfYear
    val quoteOfTheDay = philosopherQuotes[dayOfYear % philosopherQuotes.size]

    val bookList = listOf(
        "Стивен Кови - 7 навыков высокоэффективных людей",
        "Дейл Карнеги - Как завоёвывать друзей и оказывать влияние на людей",
        "Наполеон Хилл - Думай и богатей",
        "Джеймс Клир - Атомные привычки",
        "Роберт Киосаки - Богатый папа, бедный папа"
    )
    val readStatus = remember { mutableStateListOf(false, false, false, false, false) }

    Column(
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = date,
            fontSize = 24.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 8.dp)
        )
        Text(
            text = motivationalText,
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Divider(modifier = Modifier.fillMaxWidth().padding(vertical = 16.dp))
        Text(
            text = quoteOfTheDay,
            fontSize = 18.sp,
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.bodyMedium
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Мотивационные книги:",
            fontSize = 20.sp,
            textAlign = TextAlign.Start,
            modifier = Modifier.fillMaxWidth().padding(bottom = 8.dp)
        )
        LazyColumn(modifier = Modifier.weight(1f)) {
            items(bookList) { book ->
                val index = bookList.indexOf(book)
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Checkbox(
                        checked = readStatus[index],
                        onCheckedChange = { isChecked ->
                            readStatus[index] = isChecked
                        }
                    )
                    Text(
                        text = book,
                        fontSize = 16.sp,
                        modifier = Modifier.padding(start = 8.dp)
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))
        val progress = readStatus.count { it }.toFloat() / readStatus.size
        LinearProgressIndicator(
            progress = progress,
            modifier = Modifier
                .fillMaxWidth()
                .height(8.dp)
        )
        Text(
            text = "Прогресс: ${"%.0f".format(progress * 100)}%",
            fontSize = 16.sp,
            modifier = Modifier.padding(top = 8.dp)
        )
    }
}