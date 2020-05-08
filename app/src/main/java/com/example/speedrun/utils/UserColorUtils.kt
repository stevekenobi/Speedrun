package com.example.speedrun.utils

import android.graphics.Color
import android.graphics.LinearGradient
import android.graphics.Shader
import com.example.network.model.dto.StyleDto

object UserColorUtils {

    fun setSolidColor(nameStyle: StyleDto?): Int {
        if (nameStyle == null)
            return Color.parseColor("#000000")

        return Color.parseColor(nameStyle.color?.light)
    }

    fun setGradientColor(nameStyle: StyleDto?): LinearGradient {
        if (nameStyle == null)
            return LinearGradient(
                0f,0f,0f,20f,
                intArrayOf(
                    Color.parseColor("000000"),
                    Color.parseColor("000000")
                ),
                floatArrayOf(0f, 1f),
                Shader.TileMode.CLAMP)

        return LinearGradient(
            0f, 0f, 0f, 20f,
            intArrayOf(
                Color.parseColor(nameStyle.colorFrom?.light),
                Color.parseColor(nameStyle.colorTo?.light)
            ),
            floatArrayOf(0f, 1f),
            Shader.TileMode.CLAMP)
    }
}