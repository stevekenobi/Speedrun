package com.example.speedrun.ui.user

import android.content.res.Configuration
import android.view.View
import com.example.network.model.dto.UserRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import com.example.speedrun.utils.RunsTextUtils
import kotlinx.android.synthetic.main.item_user_run.view.*

class UserRunViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: UserRunDto) {
        itemView.apply {
            if (context.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT) {
                user_run_info.text = RunsTextUtils.setUserGameRun(run)
            } else {
                user_run_category.text = run.category.data.name
                user_run_place.text = RunsTextUtils.setPlaceForRecent(run.place)
                user_run_date.text = run.date
                user_run_time.text = RunTimeConverter.from(run.times.primary_t)
            }
        }
    }
}