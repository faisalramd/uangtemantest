package com.uangteman.faisalramd.mvp.contactapp.ui.contact

import com.uangteman.faisalramd.mvp.contactapp.api.ApiServiceInterface
import com.uangteman.faisalramd.mvp.contactapp.models.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class ContactPresenter: ContactContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ContactContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ContactContract.View) {
        this.view = view
    }

    override fun loadData() {
        val subscribe = api.getContacts().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: DefaultResponse<Contact>? ->
                    view.showProgress(false)
                    view.loadDataSuccess(response!!.data!!)
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun addData(contact: ContactRequest) {
        view.showProgress(true)
        val subscribe = api.addContacts(contact).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: DefaultResponse<Contact>? ->
                    view.showProgress(false)
                    view.saveDataSuccess()
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }

    override fun deleteItem(id: String) {
        view.showProgress(true)
        val subscribe = api.deleteContacts(id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({ response: DefaultResponse<Contact>? ->
                    view.showProgress(false)
                    view.saveDataSuccess()
                }, { error ->
                    view.showProgress(false)
                    view.showErrorMessage(error.localizedMessage)
                })

        subscriptions.add(subscribe)
    }
}