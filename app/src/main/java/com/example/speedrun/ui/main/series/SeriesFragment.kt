package com.example.speedrun.ui.main.series

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.network.model.dto.SeriesDto
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.utils.ItemDivideDecorator
import com.google.android.material.transition.MaterialFadeThrough
import kotlinx.android.synthetic.main.fragment_recent_games.*

class SeriesFragment : BaseFragment() {

    companion object {
        fun newInstance() = SeriesFragment()
    }

    private var viewModel: SeriesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_games, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enterTransition = MaterialFadeThrough.create()
        exitTransition = MaterialFadeThrough.create()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        viewModel?.getSeries()
    }

    private fun initUi() {
        main_rv_recent_games.layoutManager = GridLayoutManager(context, 2)
        main_rv_recent_games.addItemDecoration(ItemDivideDecorator(80))
    }

    private fun fillSeries(series: List<SeriesDto>) {
        main_rv_recent_games.adapter = SeriesAdapter(viewModel, series)
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(SeriesViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.seriesLiveData?.observe(this, Observer { seriesList ->
            if (seriesList.isNullOrEmpty())
                return@Observer

            fillSeries(seriesList)
        })

        viewModel?.seriesClickedLiveData?.observe(this, Observer { seriesId ->
            if (seriesId.isNullOrEmpty())
                return@Observer

            Toast.makeText(context, "Not Implemented", Toast.LENGTH_SHORT).show()
        })
    }

}