package com.example.vktask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.vktask.databinding.ActivityAppDetailsBinding
import com.example.vktask.repository.AppRepository

class AppDetailsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAppDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAppDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Показываем кнопку назад
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = "Описание приложения"

        val appId = intent.getIntExtra("app_id", -1)
        if (appId == -1) {
            finish()
            return
        }

        val app = AppRepository.getAppById(appId) ?: run {
            finish()
            return
        }

        with(binding) {
            appName.text = app.name
            appDescription.text = app.description
            if (app.iconRes != 0) appIcon.setImageResource(app.iconRes)

            actionButton.text = if (app.installed) "Удалить" else "Установить"

            actionButton.setOnClickListener {
                if (app.installed) AppRepository.uninstall(app.id)
                else AppRepository.install(app.id)

                actionButton.text = if (app.installed) "Удалить" else "Установить"
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        finish()
        return true
    }
}
