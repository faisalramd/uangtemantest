package com.uangteman.faisalramd.mvp.contactapp.di.component

import com.uangteman.faisalramd.mvp.contactapp.BaseApp
import com.uangteman.faisalramd.mvp.contactapp.di.module.ApplicationModule
import dagger.Component

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
@Component(modules = arrayOf(ApplicationModule::class))
interface ApplicationComponent {

    fun inject(application: BaseApp)

}