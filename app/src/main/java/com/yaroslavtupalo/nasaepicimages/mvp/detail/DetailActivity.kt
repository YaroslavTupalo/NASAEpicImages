package com.yaroslavtupalo.nasaepicimages.mvp.detail

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.yaroslavtupalo.nasaepicimages.Constants
import com.yaroslavtupalo.nasaepicimages.data.moshi.Example
import com.yaroslavtupalo.nasaepicimages.mvp.base.BaseActivity
import com.yaroslavtupalo.nasaepicimages.mvp.common.ErrorView
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

import com.yaroslavtupalo.nasaepicimages.R
import com.yaroslavtupalo.nasaepicimages.util.gone
import com.yaroslavtupalo.nasaepicimages.util.visible
import timber.log.Timber
import java.text.DateFormat
import java.text.ParsePosition
import java.text.SimpleDateFormat
import java.util.*

/**
 * Created by Yaroslav on 01.02.2018.
 */
class DetailActivity: BaseActivity(), DetailMvpView, ErrorView.ErrorListener {

    @Inject lateinit var detailPresenter: DetailPresenter

    private var position: Int? = null
    private lateinit var url: String

    companion object {

        const val DETAIL_IMAGE = "DETAIL_image"

        fun getStartIntent(context: Context, position: Int): Intent{
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_IMAGE, position)
            return intent
        }

        object DateHelper {
            const val DF_SIMPLE_STRING = "yyyy-MM-dd HH:mm:ss"
            @JvmField val DF_SIMPLE_FORMAT = object : ThreadLocal<DateFormat>() {
                override fun initialValue(): DateFormat {
                    return SimpleDateFormat(DF_SIMPLE_STRING, Locale.US)
                }
            }
        }

        fun dateParse(s: String): Date = DateHelper.DF_SIMPLE_FORMAT.get().parse(s, ParsePosition(0))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activityComponent().inject(this)
        detailPresenter.attachView(this)

        position = intent.getIntExtra(DETAIL_IMAGE, -1)
        if(position == -1){
            throw IllegalArgumentException("Detail Activity required a position of List")
        }

        setSupportActionBar(detail_toolbar)
        val actionBar = supportActionBar
        actionBar?.setDisplayHomeAsUpEnabled(true)
        title = "Epic NASA Image № $position"

        errorView?.setErrorListener(this)
        detailPresenter.getNASAEpicImageDetail(position as Int)
    }


    override fun showImage(image: Example) {
        image?.let {

            val date = dateParse(it.date)
            val calendar = Calendar.getInstance()
            calendar.time = date

            val year = calendar.get(Calendar.YEAR)
            val month = calendar.get(Calendar.MONTH)+1
            val day = calendar.get(Calendar.DAY_OF_MONTH)
            var finalMonth: String? = null

            if (month <= 9) finalMonth = "0"+month.toString() else month.toString()


            finalMonth?.let {
                Glide.with(this@DetailActivity)
                        .load(Constants.IMAGE_URL+"$year/$it/$day/png/${image.image}.png?api_key=${Constants.API_KEY}")
                        .into(imageView)
                url = Constants.IMAGE_URL+"$year/$it/$day/png/${image.image}.png?api_key=${Constants.API_KEY}"
            }

            dateTV.text = it.date
            centroid_coordinatesLat.text = it.centroid_coordinates.lat.toString()
            centroid_coordinatesLon.text = it.centroid_coordinates.lon.toString()
            attitude_quaternionsQ0.text = it.attitude_quaternions.q0.toString()
            attitude_quaternionsQ1.text = it.attitude_quaternions.q1.toString()
            attitude_quaternionsQ2.text = it.attitude_quaternions.q2.toString()
            attitude_quaternionsQ3.text = it.attitude_quaternions.q3.toString()
            lunar_positionX.text = it.lunar_j2000_position.x.toString()
            lunar_positionY.text = it.lunar_j2000_position.y.toString()
            lunar_positionZ.text = it.lunar_j2000_position.z.toString()

            webView.loadUrl(url)
        }
    }

    override fun showProgressBar(show: Boolean) {
        errorView?.gone()
        progress?.visibility = if(show) View.VISIBLE else View.GONE
    }

    override fun showError(error: Throwable) {
        layoutImageDetail?.gone()
        errorView?.visible()
        Timber.e(error, "There was a problem retrieving the image...")
    }

    override fun layoutId(): Int = R.layout.activity_detail

    override fun onReloadData() {
        detailPresenter.getNASAEpicImageDetail(position as Int)
    }

    override fun onDestroy() {
        super.onDestroy()
        detailPresenter.detachView()
    }
}