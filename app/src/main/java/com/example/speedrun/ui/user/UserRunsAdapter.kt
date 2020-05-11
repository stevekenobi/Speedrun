package com.example.speedrun.ui.user

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.R

class UserRunsAdapter(val viewModel: UserProfileViewModel?, val runs: List<UserRunDto>): RecyclerView.Adapter<UserRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_run, parent, false)
        return UserRunViewHolder(viewModel, view)
    }

    override fun getItemCount(): Int {
        return runs.size
    }

    override fun onBindViewHolder(holder: UserRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }

}