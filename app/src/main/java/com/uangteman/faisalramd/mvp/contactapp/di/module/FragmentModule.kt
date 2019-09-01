package com.uangteman.faisalramd.mvp.contactapp.di.module

import com.uangteman.faisalramd.mvp.contactapp.api.ApiServiceInterface
import com.uangteman.faisalramd.mvp.contactapp.ui.about.AboutContract
import com.uangteman.faisalramd.mvp.contactapp.ui.about.AboutPresenter
import com.uangteman.faisalramd.mvp.contactapp.ui.list.ListContract
import com.uangteman.faisalramd.mvp.contactapp.ui.list.ListPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by ogulcan on 07/02/2018.
 */
@Module
class FragmentModule {

    @Provides
    fun provideAboutPresenter(): AboutContract.Presenter {
        return AboutPresenter()
    }

    @Provides
    fun provideListPresenter(): ListContract.Presenter {
        return ListPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}