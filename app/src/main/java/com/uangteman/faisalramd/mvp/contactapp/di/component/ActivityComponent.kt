package com.uangteman.faisalramd.mvp.contactapp.di.component

import com.uangteman.faisalramd.mvp.contactapp.di.module.ActivityModule
import com.uangteman.faisalramd.mvp.contactapp.ui.main.MainActivity
import dagger.Component

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
@Component(modules = arrayOf(ActivityModule::class))
interface ActivityComponent {

    fun inject(mainActivity: MainActivity)

}