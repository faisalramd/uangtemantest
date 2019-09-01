package com.uangteman.faisalramd.mvp.contactapp.ui.contact

import com.uangteman.faisalramd.mvp.contactapp.models.Contact
import com.uangteman.faisalramd.mvp.contactapp.models.ContactRequest
import com.uangteman.faisalramd.mvp.contactapp.ui.base.BaseContract

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class ContactContract {

    interface View: BaseContract.View {
        fun showProgress(show: Boolean)
        fun showErrorMessage(error: String)
        fun loadDataSuccess(list: List<Contact>)
        fun saveDataSuccess()
    }

    interface Presenter: BaseContract.Presenter<View> {
        fun loadData()
        fun deleteItem(id: String)
        fun addData(contact: ContactRequest)
    }
}