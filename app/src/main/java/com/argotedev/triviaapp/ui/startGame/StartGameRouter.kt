package com.argotedev.triviaapp.ui.startGame

import androidx.fragment.app.Fragment
import com.argotedev.triviaapp.ui.base.BaseFragmentRouter

class StartGameRouter: BaseFragmentRouter {

    private var instance: StartGameFragment? = null

    override fun fragment(): Fragment {
        if (instance == null) {
            instance = StartGameFragment()
        }
        return instance!!
    }

}