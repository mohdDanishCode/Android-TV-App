package com.danish.common.base.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import timber.log.Timber

/**
 * Base Activity to standardize and simplify initialization for this component.
 *
 * @param layoutId Layout resource reference identifier.
 * @see AppCompatActivity
 */
abstract class BaseActivity : AppCompatActivity() {

    companion object {
        const val TAG = "BaseActivity"
        var isFirstOnCreate = true
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Timber.d("onCreate")

        if (isFirstOnCreate && savedInstanceState != null) {
            finish()
            startActivity(intent)
            overridePendingTransition(0, 0)
        }
        isFirstOnCreate = false
    }




}
