package com.example.speedrun.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.user.UserFragmentCommunicator
import kotlinx.android.synthetic.main.layout_user_game_moderate.*
import kotlinx.android.synthetic.main.layout_user_info.*
import kotlinx.android.synthetic.main.layout_user_series_moderate.*

class UserDetailsFragment : BaseFragment() {
    companion object {
        const val USER_ID_KEY = "user_id"

        fun newInstance(userId: String?): UserDetailsFragment {
            return UserDetailsFragment().withArguments {
                putString(USER_ID_KEY, userId)
            }
        }
    }

    private var viewModel: UserDetailsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getString(USER_ID_KEY)

        initUi()
        viewModel?.getGames(userId)
        viewModel?.getUserDetails(userId)
        viewModel?.getSeries(userId)
    }

    private fun initUi() {
        user_games_list.layoutManager = LinearLayoutManager(activity)
        user_series_list.layoutManager = LinearLayoutManager(activity)
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(UserDetailsViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.userDetailsLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer


            val signUpText = "Member Since: ${it.names?.international}"
            user_info_signed_up.text = signUpText
        })

        viewModel?.seriesModeratedByUser?.observe(this, Observer { seriesList ->
            if (seriesList.isNullOrEmpty())
                return@Observer

            val text = "Series(${seriesList.size})"
            user_series_moderate_title.text = text
            user_series_list.adapter = ItemsModeratedAdapter(viewModel, seriesList)
        })

        viewModel?.gamesModeratedByUser?.observe(this, Observer { gameList ->
            if (gameList.isNullOrEmpty())
                return@Observer

            val text = "Games(${gameList.size})"
            user_games_moderate_title.text = text
            user_games_list.adapter = ItemsModeratedAdapter(viewModel, gameList)
        })

        viewModel?.gameClickedLiveData?.observe(this, Observer { gameId ->
            if (gameId.isNullOrEmpty())
                return@Observer

            (activity as UserFragmentCommunicator).onGameClicked(gameId)
        })

        viewModel?.seriesClickedLiveData?.observe(this, Observer { seriesId ->
            if (seriesId.isNullOrEmpty())
                return@Observer

            (activity as UserFragmentCommunicator).onSeriesClicked(seriesId)
        })
    }
}