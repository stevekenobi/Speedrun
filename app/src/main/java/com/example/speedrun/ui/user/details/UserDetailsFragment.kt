package com.example.speedrun.ui.user.details

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.ui.base.BaseFragment
import kotlinx.android.synthetic.main.layout_user_info.*

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

        viewModel?.getGames(userId)
        viewModel?.getUserDetails(userId)
        viewModel?.getSeries(userId)
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
    }
}