package com.yaroslavtupalo.nasaepicimages.mvp.main

import android.support.v7.widget.AppCompatTextView
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import butterknife.BindView
import butterknife.ButterKnife
import javax.inject.Inject

import com.yaroslavtupalo.nasaepicimages.R
import com.yaroslavtupalo.nasaepicimages.data.moshi.Example

/**
 * Created by Yaroslav on 14.02.2018.
 */
class AdapterNASA @Inject constructor(): RecyclerView.Adapter<AdapterNASA.NASAViewHolder>() {

    private lateinit var nasaList: List<Example>
    private var clickListener: ClickListener? = null

    init {
        nasaList = emptyList<Example>()
    }

    fun setNASAList(nasaList: List<Example>){
        this.nasaList = nasaList
    }

    fun setClickListener(clickListener: ClickListener){
        this.clickListener = clickListener
    }

    override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): NASAViewHolder {
        val view = LayoutInflater
                .from(parent?.context)
                .inflate(R.layout.item_view, parent, false)
        return NASAViewHolder(view)
    }

    override fun getItemCount(): Int = nasaList.size

    override fun onBindViewHolder(holder: NASAViewHolder?, position: Int) {

        val imageInfo = nasaList[position]
        holder?.bin(imageInfo)
    }

    interface ClickListener{
        fun onItemClickListener(position: Int)
    }

    inner class NASAViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        @BindView(R.id.captionsText)
        @JvmField var captionText: AppCompatTextView? = null

        @BindView(R.id.imageText)
        @JvmField var imageText: AppCompatTextView? = null

        @BindView(R.id.dateText)
        @JvmField var dateText: AppCompatTextView? = null

        init {
            ButterKnife.bind(this, itemView)
            itemView.setOnClickListener {
                clickListener?.onItemClickListener(adapterPosition)
            }
        }

        fun bin(imageInfo: Example){

            captionText?.text = imageInfo.caption
            imageText?.text = imageInfo.image
            dateText?.text = imageInfo.date
        }
    }
}