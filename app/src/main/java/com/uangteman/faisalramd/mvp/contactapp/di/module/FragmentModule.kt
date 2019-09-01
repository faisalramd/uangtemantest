package com.uangteman.faisalramd.mvp.contactapp.di.module

import com.uangteman.faisalramd.mvp.contactapp.api.ApiServiceInterface
import com.uangteman.faisalramd.mvp.contactapp.ui.contact.ContactContract
import com.uangteman.faisalramd.mvp.contactapp.ui.contact.ContactPresenter
import dagger.Module
import dagger.Provides

/**
 * Created by ogulcan on 07/02/2018.
 */
@Module
class FragmentModule {

    @Provides
    fun provideListPresenter(): ContactContract.Presenter {
        return ContactPresenter()
    }

    @Provides
    fun provideApiService(): ApiServiceInterface {
        return ApiServiceInterface.create()
    }
}