package com.example.speedrun.ui.game

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.example.speedrun.R
import com.example.speedrun.ui.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_game_details.*

class GameDetailsFragment : BaseFragment() {

    companion object {
        const val KEY_GAME_ID = "game_id"

        fun newInstance(gameId: String?): GameDetailsFragment {
            val fragment = GameDetailsFragment()

            fragment.arguments = Bundle().apply {
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

        val gameId = arguments?.getString(KEY_GAME_ID)
        viewModel?.getGameDetails(gameId)
    }

    private fun fillGameDetails(gameName: String?, imageUrl: String?) {
        Glide.with(this).load(imageUrl).into(game_image)
        game_details_name.text = gameName
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(GameDetailsViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.gameDetailsLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            fillGameDetails(it.names?.international, it.assets.coverLarge?.uri)
        })
    }


}