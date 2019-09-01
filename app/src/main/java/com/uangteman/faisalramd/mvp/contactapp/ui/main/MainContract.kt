package com.uangteman.faisalramd.mvp.contactapp.ui.main

import com.uangteman.faisalramd.mvp.contactapp.ui.base.BaseContract

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class MainContract {

    interface View: BaseContract.View {
        fun showAboutFragment()
        fun showListFragment()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun onDrawerOptionAboutClick()
    }
}