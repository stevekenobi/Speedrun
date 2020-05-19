package com.example.speedrun.ui.main.popular

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.main.MainFragmentCommunicator
import com.example.speedrun.utils.ItemDivideDecorator
import kotlinx.android.synthetic.main.fragment_recent_games.*

class PopularGamesFragment : BaseFragment() {

    companion object {
        fun newInstance() =
            PopularGamesFragment()
    }

    var viewModel: PopularGamesViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_recent_games, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()
        viewModel?.getGames()
    }

    private fun initUi() {
        main_rv_recent_games.layoutManager = GridLayoutManager(context, 2)
        main_rv_recent_games.addItemDecoration(
            ItemDivideDecorator(
                80
            )
        )
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(PopularGamesViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.gamesLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            main_rv_recent_games.adapter =
                PopularGamesAdapter(
                    viewModel,
                    it
                )
        })

        viewModel?.gameClickedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                return@Observer

            (activity as MainFragmentCommunicator).onGameClicked(it)
        })
    }

}