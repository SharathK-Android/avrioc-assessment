package com.avrioc.assessment.ui.main.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.avrioc.assessment.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, ArticlesListFragment.newInstance())
                .commitNow()
        }
    }
}