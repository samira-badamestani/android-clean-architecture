package com.architecture.clean.core

import android.support.v4.app.FragmentManager
import com.architecture.clean.ui.home.HomeFragment

object FragmentFactory{

    fun getHomeFragment(supportFragmentManager: FragmentManager): HomeFragment {
        var fragment = supportFragmentManager.findFragmentByTag(HomeFragment.FRAGMENT_NAME)
        if (fragment == null) {
            fragment = HomeFragment()
        }
        return fragment as HomeFragment
    }

}