package com.example.vktask.repository

import com.example.vktask.model.AppInfo
import com.example.vktask.R

object AppRepository {

    // Заглушечный список приложений
    private val apps = mutableListOf(
        AppInfo(1, "Calculator Pro", "Мощный калькулятор", R.mipmap.ic_launcher, false),
        AppInfo(2, "Weather Now", "Погода в реальном времени", R.mipmap.ic_launcher, false),
        AppInfo(3, "Notes X", "Заметки и списки дел", R.mipmap.ic_launcher, false),
        AppInfo(4, "MusicStream", "Музыка онлайн", R.mipmap.ic_launcher, false)
    )

    fun getApps(): List<AppInfo> = apps

    fun getAppById(id: Int): AppInfo? = apps.find { it.id == id }

    fun install(id: Int) {
        getAppById(id)?.installed = true
    }

    fun uninstall(id: Int) {
        getAppById(id)?.installed = false
    }
}
