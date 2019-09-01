package com.uangteman.faisalramd.mvp.contactapp.ui.contact

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.uangteman.faisalramd.mvp.contactapp.R
import com.uangteman.faisalramd.mvp.contactapp.di.component.DaggerFragmentComponent
import com.uangteman.faisalramd.mvp.contactapp.di.module.FragmentModule
import com.uangteman.faisalramd.mvp.contactapp.models.Contact
import com.uangteman.faisalramd.mvp.contactapp.models.ContactRequest
import kotlinx.android.synthetic.main.fragment_contact.*
import javax.inject.Inject

/**
 * Created by faisalramd on 01/09/2019
 */
class AddContactFragment: Fragment(), ContactContract.View {

    @Inject lateinit var presenter: ContactContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): AddContactFragment {
        return AddContactFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_contact, container, false)
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

    override fun loadDataSuccess(list: List<Contact>) { }

    override fun saveDataSuccess() {
        etFirstName.setText("")
        etLastName.setText("")
        etAge.setText("")
        etPhotoUrl.setText("")

        Toast.makeText(context, "Success create contact", Toast.LENGTH_SHORT).show()
    }

    override fun showErrorMessage(error: String) {
        Toast.makeText(context, error, Toast.LENGTH_SHORT).show()
    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        aboutComponent.inject(this)
    }

    private fun initView() {
        showProgress(false)
        btnSave.setOnClickListener {
            try {
                val firstName = etFirstName.text.toString()
                val lastName = etLastName.text.toString()
                val age = Integer.parseInt(etAge.text.toString())
                val photoUrl = etPhotoUrl.text.toString()
                val contact = ContactRequest(firstName, lastName, age, photoUrl)

                presenter.addData(contact)
            } catch (e: NumberFormatException) {
                Toast.makeText(context, "Please complete your input", Toast.LENGTH_SHORT).show()
            }
        }
    }

    companion object {
        val TAG: String = "AddContactFragment"
    }
}