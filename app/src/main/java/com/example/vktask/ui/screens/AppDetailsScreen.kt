package com.example.vktask.ui.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.vktask.ui.viewmodel.AppDetailsViewModel

@Composable
fun AppDetailsScreen(
    id: Int,
    vm: AppDetailsViewModel,
    onBack: () -> Unit
) {
    val app by vm.app.collectAsState()

    LaunchedEffect(Unit) { vm.load(id) }

    Column(Modifier.fillMaxSize().padding(16.dp)) {

        Text(app?.name ?: "", style = MaterialTheme.typography.headlineMedium)

        Spacer(Modifier.height(8.dp))

        Text(app?.description ?: "", style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(16.dp))

        Button(onClick = { vm.toggleInstall(id) }) {
            Text(if (app?.installed == true) "Удалить" else "Установить")
        }

        Spacer(Modifier.height(16.dp))

    }
}
