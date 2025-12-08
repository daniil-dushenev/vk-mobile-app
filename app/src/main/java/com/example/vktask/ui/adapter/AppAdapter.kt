package com.example.vktask.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.vktask.databinding.ItemAppBinding
import com.example.vktask.model.AppInfo


class AppAdapter(
    private val apps: List<AppInfo>,
    private val onInstallClick: (AppInfo) -> Unit,
    private val onUninstallClick: (AppInfo) -> Unit,
    private val onOpenDetails: (AppInfo) -> Unit
) : RecyclerView.Adapter<AppAdapter.AppViewHolder>() {

    inner class AppViewHolder(val binding: ItemAppBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppViewHolder {
        val binding = ItemAppBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return AppViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AppViewHolder, position: Int) {
        val app = apps[position]
        with(holder.binding) {
            appName.text = app.name
            appIcon.setImageResource(app.iconRes)

            actionButton.text = if (app.installed) "Удалить" else "Установить"

            actionButton.setOnClickListener {
                if (app.installed) onUninstallClick(app)
                else onInstallClick(app)
            }

            root.setOnClickListener {
                onOpenDetails(app)
            }
        }
    }

    override fun getItemCount(): Int = apps.size
}
