package com.uangteman.faisalramd.mvp.contactapp.di.component

import com.uangteman.faisalramd.mvp.contactapp.di.module.FragmentModule
import com.uangteman.faisalramd.mvp.contactapp.ui.about.AboutFragment
import com.uangteman.faisalramd.mvp.contactapp.ui.list.ListFragment
import dagger.Component

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
@Component(modules = arrayOf(FragmentModule::class))
interface FragmentComponent {

    fun inject(aboutFragment: AboutFragment)

    fun inject(listFragment: ListFragment)

}