package com.yaroslavtupalo.nasaepicimages

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.yaroslavtupalo.nasaepicimages.data.moshi.Example
import com.yaroslavtupalo.nasaepicimages.mvp.base.BaseActivity
import com.yaroslavtupalo.nasaepicimages.mvp.common.ErrorView
import com.yaroslavtupalo.nasaepicimages.mvp.detail.DetailActivity
import com.yaroslavtupalo.nasaepicimages.mvp.main.AdapterNASA
import com.yaroslavtupalo.nasaepicimages.mvp.main.MainMvpView
import com.yaroslavtupalo.nasaepicimages.mvp.main.MainPresenter
import com.yaroslavtupalo.nasaepicimages.util.gone
import com.yaroslavtupalo.nasaepicimages.util.visible
import kotlinx.android.synthetic.main.activity_main.*
import timber.log.Timber
import javax.inject.Inject

class MainActivity: BaseActivity(), MainMvpView, ErrorView.ErrorListener, AdapterNASA.ClickListener {

    @Inject lateinit var mainPresenter: MainPresenter
    @Inject lateinit var adapterNASA: AdapterNASA

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent().inject(this)
        mainPresenter.attachView(this)

        setSupportActionBar(main_toolbar)

        swipeToRefresh?.apply {
            setProgressBackgroundColorSchemeResource(R.color.primary)
            setColorSchemeResources(R.color.white)
            setOnRefreshListener{mainPresenter.getNASAImagesInfo()}
        }

        adapterNASA.setClickListener(this)

        recyclerNASAImages?.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = adapterNASA
        }

        viewError?.setErrorListener(this)

        mainPresenter.getNASAImagesInfo()
    }

    override fun onItemClickListener(position: Int) {
        startActivity(DetailActivity.getStartIntent(this@MainActivity, position))
    }

    override fun showNASAEpicImagesInfo(result: List<Example>) {
        adapterNASA.apply {
            setNASAList(result)
            notifyDataSetChanged()
        }
        recyclerNASAImages?.visible()
        swipeToRefresh?.visible()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            if (recyclerNASAImages?.visibility == View.VISIBLE && adapterNASA.itemCount > 0) {
                swipeToRefresh?.isRefreshing = true
            } else {
                progressBar?.visible()
                recyclerNASAImages?.gone()
                swipeToRefresh?.gone()
            }

            viewError?.gone()
        } else {
            swipeToRefresh?.isRefreshing = false
            progressBar?.gone()
        }
    }

    override fun showError(error: Throwable) {
        recyclerNASAImages?.gone()
        swipeToRefresh?.gone()
        viewError?.visible()
        Timber.e(error, "There was an error retrieving the NASA Epic Images")
    }

    override fun layoutId(): Int = R.layout.activity_main

    override fun onReloadData() {
        mainPresenter.getNASAImagesInfo()
    }

    override fun onDestroy() {
        super.onDestroy()
        mainPresenter.detachView()
    }
}
