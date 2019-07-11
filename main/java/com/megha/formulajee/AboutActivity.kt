package com.megha.formulajee

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_about.*

class AboutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about)

        abouttoolbar.setNavigationIcon(R.drawable.ic_back_arrow)
        abouttoolbar.setNavigationOnClickListener { onBackPressed() }
    }
}
