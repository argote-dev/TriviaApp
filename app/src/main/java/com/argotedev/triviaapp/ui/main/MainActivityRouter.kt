package com.argotedev.triviaapp.ui.main

import android.content.Context
import android.content.Intent
import com.argotedev.triviaapp.ui.base.BaseActivityRouter

class MainActivityRouter: BaseActivityRouter {
    override fun intent(activity: Context): Intent = Intent(activity, MainActivity::class.java)
}