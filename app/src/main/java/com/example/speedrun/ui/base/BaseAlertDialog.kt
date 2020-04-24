package com.example.speedrun.ui.base

import android.app.AlertDialog
import android.content.DialogInterface

object BaseAlertDialog {
    fun builder(
        activity: BaseActivity,
        title: Int,
        message: Int,
        positiveMessage: Int,
        positiveButtonClickListener: DialogInterface.OnClickListener,
        negativeMessage: Int,
        negativeButtonClickListener: DialogInterface.OnClickListener,
        cancelable: Boolean
    ): AlertDialog {
        return AlertDialog.Builder(activity)
            .setTitle(title)
            .setMessage(message)
            .setPositiveButton(positiveMessage, positiveButtonClickListener)
            .setNegativeButton(negativeMessage, negativeButtonClickListener)
            .setCancelable(cancelable)
            .create()
    }
}