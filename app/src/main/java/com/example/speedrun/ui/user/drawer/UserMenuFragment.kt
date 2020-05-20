package com.example.speedrun.ui.user.drawer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.example.network.model.dto.UserDto
import com.example.network.utils.NetworkConstants
import com.example.speedrun.R
import com.example.speedrun.extensions.withArguments
import com.example.speedrun.ui.base.BaseFragment
import com.example.speedrun.utils.UserColorUtils
import kotlinx.android.synthetic.main.fragment_user_menu.*

class UserMenuFragment : BaseFragment() {

    companion object {
        const val KEY_USER_ID = "user_id"
        fun newInstance(userId: String?): UserMenuFragment {

            return UserMenuFragment().withArguments {
                putString(KEY_USER_ID, userId)
            }

        }
    }

    var viewModel: UserMenuViewModel? = null

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_user_menu, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val userId = arguments?.getString(KEY_USER_ID)
        viewModel?.getUserDetails(userId)
    }

    private fun initUserDetails(user: UserDto) {
        user_name.text = user.names?.international

        val style = user.nameStyle?.style
        if (style == NetworkConstants.STYLE_SOLID) {
            user_name.setTextColor(UserColorUtils.setSolidColor(user.nameStyle))
        } else if (style == NetworkConstants.STYLE_GRADIENT) {
            user_name.paint.shader = UserColorUtils.setGradientColor(user.nameStyle)
        }
    }

    override fun initViewModel() {
        viewModel = viewModelFactory.create(UserMenuViewModel::class.java)
    }

    override fun observeViewModel() {
        viewModel?.userDetailsLiveData?.observe(this, Observer {
            if (it == null)
                return@Observer

            initUserDetails(it)
        })
    }
}