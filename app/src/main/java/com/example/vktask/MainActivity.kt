package com.example.vktask

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.vktask.databinding.ActivityMainBinding
import com.example.vktask.repository.AppRepository
import com.example.vktask.ui.adapter.AppAdapter
import android.content.Intent

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: AppAdapter   // <-- ключевой фикс

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val apps = AppRepository.getApps()

        // теперь adapter — переменная класса, доступна в лямбдах
        adapter = AppAdapter(
            apps,
            onInstallClick = { app ->
                AppRepository.install(app.id)
                adapter.notifyDataSetChanged()
            },
            onUninstallClick = { app ->
                AppRepository.uninstall(app.id)
                adapter.notifyDataSetChanged()
            },
            onOpenDetails = { app ->
                val intent = Intent(this, AppDetailsActivity::class.java)
                intent.putExtra("app_id", app.id)
                startActivity(intent)
            }
        )

        binding.appList.layoutManager = LinearLayoutManager(this)
        binding.appList.adapter = adapter
    }
}
