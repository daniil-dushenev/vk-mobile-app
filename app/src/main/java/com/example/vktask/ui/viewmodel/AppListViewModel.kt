package com.example.vktask.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vktask.repository.AppRepository
import com.example.vktask.data.AppEntity
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class AppListViewModel(private val repo: AppRepository) : ViewModel() {

    private val _apps = MutableStateFlow<List<AppEntity>>(emptyList())
    val apps = _apps.asStateFlow()

    init {
        viewModelScope.launch {
            repo.seedInitialData()
            load()
        }
    }

    fun load() {
        viewModelScope.launch {
            _apps.value = repo.getApps()
        }
    }

    fun toggle(id: Int) {
        viewModelScope.launch {
            repo.toggleInstall(id)
            load()
        }
    }
}
