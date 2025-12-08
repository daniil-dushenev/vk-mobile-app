package com.example.vktask.ui.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vktask.ui.viewmodel.AppListViewModel

@Composable
fun AppListScreen(
    vm: AppListViewModel,
    onOpenDetails: (Int) -> Unit
) {
    val apps by vm.apps.collectAsState()

    LazyColumn(Modifier.fillMaxSize().padding(16.dp)) {
        items(apps) { app ->
            Row(
                Modifier.fillMaxWidth().padding(8.dp).clickable {
                    onOpenDetails(app.id)
                }
            ) {
                Column {
                    Text(app.name, style = MaterialTheme.typography.titleMedium)
                    Text(app.description)
                }
                Spacer(Modifier.weight(1f))
                Button(onClick = { vm.toggle(app.id) }) {
                    Text(if (app.installed) "Удалить" else "Установить")
                }
            }
        }
    }
}
