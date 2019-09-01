package com.uangteman.faisalramd.mvp.contactapp.ui.contact

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.helper.ItemTouchHelper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uangteman.faisalramd.mvp.contactapp.R
import com.uangteman.faisalramd.mvp.contactapp.di.component.DaggerFragmentComponent
import com.uangteman.faisalramd.mvp.contactapp.di.module.FragmentModule
import com.uangteman.faisalramd.mvp.contactapp.models.Contact
import com.uangteman.faisalramd.mvp.contactapp.util.SwipeToDelete
import kotlinx.android.synthetic.main.fragment_contact_list.*
import javax.inject.Inject

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class ContactFragment: Fragment(), ContactContract.View, ContactAdapter.onItemClickListener {

    @Inject lateinit var presenter: ContactContract.Presenter

    private lateinit var rootView: View

    public lateinit var listener: onItemClickListener

    fun newInstance(): ContactFragment {
        return ContactFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater!!.inflate(R.layout.fragment_contact_list, container, false)
        return rootView
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        presenter.attach(this)
        presenter.subscribe()
        initView()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        presenter.unsubscribe()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            progressBar.visibility = View.VISIBLE
        } else {
            progressBar.visibility = View.GONE
        }
    }

    override fun showErrorMessage(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_LONG).show()
    }

    override fun loadDataSuccess(list: List<Contact>) {
        var adapter = ContactAdapter(context!!, list.toMutableList(), this)
        recyclerView!!.setLayoutManager(LinearLayoutManager(activity))
        recyclerView!!.setAdapter(adapter)

        val swipeHandler = object : SwipeToDelete(context!!) {
            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val adapter = recyclerView.adapter as ContactAdapter
                adapter.removeAt(viewHolder.adapterPosition)
            }
        }

        val itemTouchHelper = ItemTouchHelper(swipeHandler)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    override fun saveDataSuccess() { }

    override fun itemRemoveClick(contact: Contact) {
        presenter.deleteItem(contact.id)
    }

    override fun itemDetail(postId: String) {
        listener.itemDetail(postId)
    }

    private fun injectDependency() {
        val listComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        listComponent.inject(this)
    }

    private fun initView() {
        presenter.loadData()
    }

    companion object {
        val TAG: String = "ContactFragment"
    }

    interface onItemClickListener {
        fun itemDetail(postId : String)
    }
}