package com.uangteman.faisalramd.mvp.contactapp.ui.about

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.uangteman.faisalramd.mvp.contactapp.R
import com.uangteman.faisalramd.mvp.contactapp.di.component.DaggerFragmentComponent
import com.uangteman.faisalramd.mvp.contactapp.di.module.FragmentModule
import kotlinx.android.synthetic.main.fragment_about.*
import javax.inject.Inject

/**
 * Created by ogulcan on 07/02/2018.
 * modified by faisalramd on 01/09/2019
 */
class AboutFragment: Fragment(), AboutContract.View {

    @Inject lateinit var presenter: AboutContract.Presenter

    private lateinit var rootView: View

    fun newInstance(): AboutFragment {
        return AboutFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        injectDependency()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        rootView = inflater.inflate(R.layout.fragment_about, container, false)
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

    override fun loadMessageSuccess(message: String) {
        aboutText.text = getString(R.string.about_text)
        aboutText.visibility = View.VISIBLE
    }

    private fun injectDependency() {
        val aboutComponent = DaggerFragmentComponent.builder()
                .fragmentModule(FragmentModule())
                .build()

        aboutComponent.inject(this)
    }

    private fun initView() {
        presenter.loadMessage()
    }

    companion object {
        val TAG: String = "AboutFragment"
    }
}