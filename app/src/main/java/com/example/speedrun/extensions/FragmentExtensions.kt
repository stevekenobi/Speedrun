package com.example.speedrun.extensions

import android.os.Bundle
import androidx.fragment.app.Fragment

inline fun <T: Fragment> T.withArguments(argsBuilder: Bundle.() -> Unit): T =
    this.apply {
        arguments = Bundle().apply(argsBuilder)
    }