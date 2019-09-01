package com.uangteman.faisalramd.mvp.contactapp.ui.list

import com.uangteman.faisalramd.mvp.contactapp.api.ApiServiceInterface
import com.uangteman.faisalramd.mvp.contactapp.models.*
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Function3
import io.reactivex.schedulers.Schedulers

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class ListPresenter: ListContract.Presenter {

    private val subscriptions = CompositeDisposable()
    private val api: ApiServiceInterface = ApiServiceInterface.create()
    private lateinit var view: ListContract.View

    override fun subscribe() {

    }

    override fun unsubscribe() {
        subscriptions.clear()
    }

    override fun attach(view: ListContract.View) {
        this.view = view
    }

    override fun loadData() {
        var subscribe = api.getContacts().subscribeOn(Schedulers.io())
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

    private fun createDetailsViewModel(posts: List<Post>, users: List<User>, albums: List<Album>): DetailsViewModel {
        val postList = posts.take(30)
        val userList = users.take(30)
        val albumList = albums.take(30)
        return DetailsViewModel(postList, userList, albumList)
    }

    override fun deleteItem(item: Post) {
        //api.deleteUser(item.id)
    }
}