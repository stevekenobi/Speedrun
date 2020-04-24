package com.example.speedrun.ui.main

import android.view.View
import com.example.network.model.dto.LatestRunDto
import com.example.speedrun.ui.base.BaseViewHolder
import com.example.speedrun.utils.RunTimeConverter
import kotlinx.android.synthetic.main.item_latest_run.view.*
import org.ocpsoft.prettytime.PrettyTime
import java.util.*

class LatestRunViewHolder(itemView: View) : BaseViewHolder(itemView) {

    init {
        viewHolderComponent()?.inject(this)
    }

    fun bind(run: LatestRunDto) {
        itemView.apply {
            run_username.text = run.username
            run_category_name.text = run.categoryName
            run_time.text = RunTimeConverter.from(run.timeCompleted)
            run_game_name.text = run.gameName

            val prettyTime = PrettyTime()
            run_date.text = prettyTime.format(run.dateCompleted)
        }
    }
}