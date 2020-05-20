package com.example.speedrun.ui.user.runs

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.model.UserGameModel
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.utils.ItemDivideDecorator
import kotlinx.android.synthetic.main.fragment_user_runs.*

class UserRunsFragment : BaseFragment() {
    companion object {
        const val KEY_USER_ID = "user_id"

        fun newInstance(userId: String?): UserRunsFragment {

            return UserRunsFragment().withArguments {
                putString(KEY_USER_ID, userId)
            }
        }
    }

    var viewModel: UserRunsViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_runs, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initUi()

        val userId = arguments?.getString(KEY_USER_ID)
        viewModel?.getUserRuns(userId)
    }

    private fun initUi() {
        rv_user_runs.apply {
            layoutManager = LinearLayoutManager(context)
            val itemDecoration = ItemDivideDecorator(80)
            addItemDecoration(itemDecoration)
        }
    }

    private fun updateUserRuns(gameList: List<UserGameModel>) {
        rv_user_runs.adapter =
            UserGamesAdapter(
                viewModel,
                gameList
            )
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(UserRunsViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.userRunsLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            updateUserRuns(it)
        })

        viewModel?.gameClickedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                return@Observer
//
//            val intent = Intent(this@UserProfileActivity, GameDetailsActivity::class.java)
//            intent.putExtra(Constants.EXTRA_GAME_ID, it)
//            startActivity(intent)
        })

        viewModel?.runClickedLiveData?.observe(this, Observer {
            if (it.isNullOrEmpty())
                return@Observer

//            val intent = Intent(this@UserProfileActivity, RunDetailsActivity::class.java)
//            intent.putExtra(Constants.EXTRA_RUN_ID, it)
//            startActivity(intent)
        })
    }
}