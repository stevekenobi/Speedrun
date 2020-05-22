package com.example.speedrun.ui.game.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.network.model.dto.ImageDto
import com.example.network.model.dto.LevelDto
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.ui.game.GameDetailsActivity
import kotlinx.android.synthetic.main.fragment_game_details.*

class GameDetailsFragment : BaseFragment() {

    companion object {
        const val KEY_GAME_ID = "game_id"

        fun newInstance(gameId: String?): GameDetailsFragment {
            val fragment =
                GameDetailsFragment()

            fragment.withArguments {
                putString(KEY_GAME_ID, gameId)
            }

            return fragment
        }
    }

    var viewModel: GameDetailsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_game_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        val gameId = arguments?.getString(KEY_GAME_ID)
        viewModel?.getGameDetails(gameId)
    }

    private fun initUi() {
    }

    private fun fillGameDetails(gameName: String?, image: ImageDto?) {
        Glide
            .with(this)
            .load(image?.uri)
            .apply(RequestOptions().override(image?.width ?: 130, image?.height ?: 130))
            .into(game_image)
        game_details_name.text = gameName
    }

    private fun fillLevelsList(levels: List<LevelDto>) {
        val headerList = listOf("Full Game Leaderboard", "Level Leaderboards")

        val listChild = HashMap<String, List<LevelDto>>()
        listChild[headerList[1]] = levels

        game_levels_list.setAdapter(
            ExpandableListAdapter(
                viewModel,
                headerList,
                listChild
            )
        )
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(GameDetailsViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.gameDetailsLiveData?.observe(this, Observer { game ->
            if (game == null)
                return@Observer

            fillGameDetails(game.names?.international, game.assets.coverLarge)
            fillLevelsList(game.levels.data)
        })

        viewModel?.levelSelectedLiveData?.observe(this, Observer { levelId ->
            (activity as GameDetailsActivity).onILClicked(levelId)
        })
    }


}