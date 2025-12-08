package com.example.vktask.repository

import com.example.vktask.data.AppDao
import com.example.vktask.data.AppEntity

class AppRepository(private val dao: AppDao) {

    suspend fun getApps(): List<AppEntity> = dao.getAll()

    suspend fun getApp(id: Int): AppEntity? = dao.getById(id)

    suspend fun toggleInstall(id: Int) {
        val app = dao.getById(id) ?: return
        dao.update(app.copy(installed = !app.installed))
    }

    suspend fun seedInitialData() {
        if (dao.getAll().isEmpty()) {
            dao.insert(AppEntity(1, "Calculator Pro", "Мощный калькулятор"))
            dao.insert(AppEntity(2, "Notes X", "Записки и списки"))
            dao.insert(AppEntity(3, "MusicStream", "Онлайн музыка"))
            dao.insert(AppEntity(4, "Weather Now", "Погода сейчас"))
        }
    }
}
