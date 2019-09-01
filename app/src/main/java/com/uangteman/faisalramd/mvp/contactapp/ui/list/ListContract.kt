package com.uangteman.faisalramd.mvp.contactapp.ui.list

import com.uangteman.faisalramd.mvp.contactapp.models.Contact
import com.uangteman.faisalramd.mvp.contactapp.ui.base.BaseContract
import com.uangteman.faisalramd.mvp.contactapp.models.DetailsViewModel
import com.uangteman.faisalramd.mvp.contactapp.models.Post

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class ListContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Contact>)
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun deleteItem(item: Post)
    }
}