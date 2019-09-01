package com.uangteman.faisalramd.mvp.contactapp

import android.app.Application
import com.uangteman.faisalramd.mvp.contactapp.di.component.ApplicationComponent
import com.uangteman.faisalramd.mvp.contactapp.di.component.DaggerApplicationComponent
import com.uangteman.faisalramd.mvp.contactapp.di.module.ApplicationModule

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class BaseApp: Application() {

    lateinit var component: ApplicationComponent

    override fun onCreate() {
        super.onCreate()

        instance = this
        setup()

        if (BuildConfig.DEBUG) {
            // Maybe TimberPlant etc.
        }
    }

    fun setup() {
        component = DaggerApplicationComponent.builder()
                .applicationModule(ApplicationModule(this)).build()
        component.inject(this)
    }

    fun getApplicationComponent(): ApplicationComponent {
        return component
    }

    companion object {
        lateinit var instance: BaseApp private set
    }
}