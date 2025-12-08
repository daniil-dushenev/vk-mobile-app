package com.example.vktask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktask.data.AppEntity
import com.example.vktask.repository.AppRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class AppDetailsViewModel(private val repo: AppRepository) : ViewModel() {

    private val _app = MutableStateFlow<AppEntity?>(null)
    val app = _app.asStateFlow()

    fun load(id: Int) {
        viewModelScope.launch {
            _app.value = repo.getApp(id)
        }
    }

    fun toggleInstall(id: Int) {
        viewModelScope.launch {
            repo.toggleInstall(id)
            load(id)
        }
    }
}
