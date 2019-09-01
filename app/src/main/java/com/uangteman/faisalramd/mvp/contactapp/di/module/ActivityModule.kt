package com.uangteman.faisalramd.mvp.contactapp.di.module

import android.app.Activity
import com.uangteman.faisalramd.mvp.contactapp.ui.main.MainContract
import com.uangteman.faisalramd.mvp.contactapp.ui.main.MainPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by ogulcan on 07/02/2018.
 */
@Module
class ActivityModule(private var activity: Activity) {

    @Provides
    fun provideActivity(): Activity {
        return activity
    }

    @Provides
    fun providePresenter(): MainContract.Presenter {
        return MainPresenter()
    }

}