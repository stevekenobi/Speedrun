package com.example.speedrun.ui.user.runs

import android.content.res.Configuration
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.R
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.item_user_run.view.*

class UserRunsAdapter(val viewModel: UserRunsViewModel?, val runs: List<UserRunDto>, val showMills: Boolean): RecyclerView.Adapter<UserRunsAdapter.UserRunViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserRunViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_user_run, parent, false)
        return UserRunViewHolder(viewModel, view, showMills)
    }

    override fun getItemCount(): Int {
        return runs.size
    }

    override fun onBindViewHolder(holder: UserRunViewHolder, position: Int) {
        holder.bind(runs[position])
    }

    class UserRunViewHolder(val viewModel: UserRunsViewModel?, itemView: View, val showMills: Boolean) : RecyclerView.ViewHolder(itemView) {
        fun bind(run: UserRunDto) {
            itemView.apply {
                setOnClickListener {
                    viewModel?.runClickedLiveData?.value = run.id
                }
                if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                    user_run_info.text = RunsTextUtils.setUserGameRun(run, showMills)
                } else {
                    user_run_category.text = run.category.data.name
                    user_run_place.text = RunsTextUtils.setPlaceForLeaderboard(run.place)
                    user_run_date.text = run.date
                    user_run_time.text = RunTimeConverter.from(run.times.primary_t, showMills)
                }
            }
        }
    }

}