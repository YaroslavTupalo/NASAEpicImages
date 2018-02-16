package com.yaroslavtupalo.nasaepicimages.mvp.base

import android.os.Bundle
import android.support.annotation.LayoutRes
import android.support.v4.util.LongSparseArray
import android.support.v7.app.AppCompatActivity
import android.view.MenuItem
import butterknife.ButterKnife
import com.yaroslavtupalo.nasaepicimages.app.MvpNASAApplication
import com.yaroslavtupalo.nasaepicimages.dagger.component.ActivityComponent
import com.yaroslavtupalo.nasaepicimages.dagger.component.ConfigPersistentComponent
import com.yaroslavtupalo.nasaepicimages.dagger.component.DaggerConfigPersistentComponent
import com.yaroslavtupalo.nasaepicimages.dagger.module.ActivityModule
import timber.log.Timber
import java.util.concurrent.atomic.AtomicLong

/**
 * Created by Yaroslav on 01.02.2018.
 */
abstract class BaseActivity: AppCompatActivity() {

    private var activityComponent: ActivityComponent? = null
    private var activityId = 0L

    @LayoutRes abstract fun layoutId(): Int

    companion object {
        private val KEY_ACTIVITY_ID = "KEY_ACTIVITY_ID"
        private val NEXT_ID = AtomicLong(0)
        private val componentsArray = LongSparseArray<ConfigPersistentComponent>()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layoutId())
        ButterKnife.bind(this)

        // Create the ActivityComponent and reuses cached ConfigPersistentComponent if this is
        // being called after a configuration change.
        activityId = savedInstanceState?.getLong(KEY_ACTIVITY_ID) ?: NEXT_ID.getAndIncrement()
        val configPersistentComponent: ConfigPersistentComponent
        if(componentsArray.get(activityId) == null){
            Timber.i("Creating new ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = DaggerConfigPersistentComponent.builder()
                    .appComponent(MvpNASAApplication[this].component)
                    .build()
            componentsArray.put(activityId, configPersistentComponent)
        } else {
            Timber.i("Reusing ConfigPersistentComponent id=%d", activityId)
            configPersistentComponent = componentsArray.get(activityId)
        }
        activityComponent = configPersistentComponent.activityComponent(ActivityModule(this))
        activityComponent?.inject(this)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putLong(KEY_ACTIVITY_ID, activityId)
    }

    override fun onDestroy() {
        if (!isChangingConfigurations) {
            Timber.i("Clearing ConfigPersistentComponent id=%d", activityId)
            componentsArray.remove(activityId)
        }
        super.onDestroy()
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean = when(item?.itemId){
        android.R.id.home -> {
            finish()
            true
        }
        else -> super.onOptionsItemSelected(item)
    }

    fun activityComponent() = activityComponent as ActivityComponent
}